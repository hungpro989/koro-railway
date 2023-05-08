package com.example.test.service;

import com.example.test.dto.ProductCreateDTO;
import com.example.test.dto.ProductDTOAdmin;
import com.example.test.dto.ProductDetailCreateDTO;
import com.example.test.models.Category;
import com.example.test.models.CategoryProduct;
import com.example.test.models.Product;
import com.example.test.models.ProductDetail;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.ProductDetailRepository;
import com.example.test.repository.ProductRepository;
import com.example.test.serviceImpl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.example.test.common.common.toSlug;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    CategoryProductService categoryProductService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDTOAdmin> getAllProduct() {
        List<ProductDTOAdmin> listDto = new ArrayList<>();
        List<Product> list = productRepository.findAll();
        for (Product product : list) {
            listDto.add(new ProductDTOAdmin(product));
        }
        return listDto;
    }

    @Override
    public ProductDTOAdmin getProducById(Integer id) {
        ProductDTOAdmin dto = new ProductDTOAdmin();
        if(productRepository.findById(id).isPresent()){
            Product p = productRepository.findById(id).get();
            dto = new ProductDTOAdmin(p);
            return dto;
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean save(Product product) {
        try{
            product.setSlug(toSlug(product.getName()));
            productRepository.save(product);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product checkExistName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public boolean checkExistId(Integer id) {
        Product p = productRepository.findById(id).orElse(null);
        if(p!=null){
            //tồn tại trong db
            return true;
        }
        //ko tồn tại trong db
        return false;
    }

    @Override
    public String updateAndCheckProduct(ProductCreateDTO productCreateDTO, Integer id) {
        ProductDTOAdmin proDto = getProducById(productCreateDTO.getId());
        if(id == productCreateDTO.getId() && proDto!=null){
            Product p = new Product(productCreateDTO);
            //check name đã có trong DB hay chưa
            if(checkExistName(productCreateDTO.getName())==null){
                return createProduct(productCreateDTO, id, p);
            }else if(productCreateDTO.getName().equalsIgnoreCase(proDto.getName())){
                return createProduct(productCreateDTO, id, p);
            }else{
                return "false01";
            }
        }
        return "false02";
    }

    @Override
    public String copyProduct(Integer id) {
        ProductDTOAdmin productDTOAdmin = getProducById(id);
        ProductCreateDTO productCreateDTO = new ProductCreateDTO(productDTOAdmin);
        Product p = new Product(productCreateDTO);
        p.setName(p.getName()+" copy");
        if(save(p)){
            productDTOAdmin.getProductDetail().forEach(var -> {
                        ProductDetail productDetail = new ProductDetail(var);
                        productDetail.setId(null);
                        productDetail.setCodeName(var.getCodeName()+" copy");
                        productDetail.setProducts(p);
                        productDetailService.save(productDetail);
                    }
            );
            productDTOAdmin.getCategoryProduct().forEach(var->{
                Category category =  categoryRepository.findById(var.getId()).orElse(null);
                if (category!=null){
                    CategoryProduct categoryProduct = new CategoryProduct();
                    categoryProduct.setProduct(p);
                    categoryProduct.setCategory(category);
                    categoryProductService.save(categoryProduct);
                }
            });
            return "true";
        }
        return "false";
    }

    //giảm valid
    @Override
    public void scaleDownQuantityValid(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setValidQuantity(pd.getValidQuantity()-quantity);
        productDetailRepository.save(pd);
    }
    //tăng valid
    @Override
    public void scaleUpQuantityValid(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setValidQuantity(pd.getValidQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    //tăng hold
    @Override
    public void scaleUpQuantityHold(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setHoldQuantity(pd.getHoldQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    //giảm hold
    @Override
    public void scaleDownQuantityHold(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setHoldQuantity(pd.getHoldQuantity()-quantity);
        productDetailRepository.save(pd);
    }
    //tăng total_quantity
    @Override
    public void scaleUpTotalQuantity(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setTotalQuantity(pd.getTotalQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    // giảm total_quantity
    @Override
    public void scaleDownTotalQuantity(Integer quantity, ProductDetailCreateDTO dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setTotalQuantity(pd.getTotalQuantity()-quantity);
        productDetailRepository.save(pd);
    }
    // xử lý số lượng khi: tạo đơn
    @Override
    public void handleWhenCreateOrder(Integer quantity, ProductDetail pdInput) {
        ProductDetail pd = pdInput;
        pd.setValidQuantity(pd.getValidQuantity()-quantity);
        pd.setHoldQuantity(pd.getHoldQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    // xử lý số lượng khi: in đơn
    @Override
    public void handleWhenPrintBillOrder(Integer quantity, ProductDetail pd) {
//        ProductDetail pd = new ProductDetail(dto);
        pd.setHoldQuantity(pd.getHoldQuantity()-quantity);
        productDetailRepository.save(pd);
    }
    // xử lý số lượng khi: hoàn đơn
    @Override
    public void handleWhenReFundOrder(Integer quantity, ProductDetail dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setValidQuantity(pd.getValidQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    // xử lý số lượng khi: thêm stock ở trạng thái đã hoàn thành
    @Override
    public void handleWhenCreateStock(Integer quantity, ProductDetail dto) {
        ProductDetail pd = new ProductDetail(dto);
        pd.setTotalQuantity(pd.getTotalQuantity()+quantity);
        pd.setValidQuantity(pd.getValidQuantity()+quantity);
        productDetailRepository.save(pd);
    }
    // xử lý số lượng khi: huỷ stock ở trạng thái hoàn thành
    @Override
    public void handleWhenCancelCreateStock(Integer quantity, ProductDetail dto) {
        ProductDetail pd = new ProductDetail(dto);
        if(pd.getTotalQuantity()>quantity) {
            pd.setTotalQuantity(pd.getTotalQuantity() - quantity);
            pd.setValidQuantity(pd.getValidQuantity() - quantity);
            productDetailRepository.save(pd);
        }
    }

    private String createProduct(ProductCreateDTO productCreateDTO, Integer id, Product p) {
        System.out.println(productCreateDTO.getCategoryProduct());
        if(!categoryProductService.findCategoryProductByProductId(id).isEmpty()){
            if(!productCreateDTO.getCategoryProduct().isEmpty()){
                categoryProductService.deleteById(p.getId());
            }
        }
        if(save(p)){
            createProductDetail(productCreateDTO,p);
            createCategoryProduct(productCreateDTO, p);
        }
        return "true";
    }


    public void createCategoryProduct(@RequestBody ProductCreateDTO productCreateDTO, Product p) {
        if(productCreateDTO.getCategoryProduct()!= null){
            productCreateDTO.getCategoryProduct().forEach(var->{
                Category category = new Category();
                category = categoryRepository.findById(var.getCategoryId()).orElse(null);
                if (category!=null){
                    CategoryProduct categoryProduct = new CategoryProduct();
                    categoryProduct.setProduct(p);
                    categoryProduct.setCategory(category);
                    categoryProductService.save(categoryProduct);
                }
            });
        }
    }
    //tạo product detail
    public void createProductDetail(@RequestBody ProductCreateDTO productCreateDTO, Product p) {
        if(productCreateDTO.getProductDetail()!=null){
            productCreateDTO.getProductDetail().forEach(var -> {
                        ProductDetail productDetail = new ProductDetail(var);
                        productDetail.setProducts(p);
                        productDetailService.save(productDetail);
                    }
            );
        }
    }
    //tạo đơn: giảm quantity_valid, tăng quantity_hold

    //in đơn: giảm quantity_hold

    //hoàn đơn, huỷ đơn: tăng quantity_valid

    //nhập stock: tăng quantity_valid, tăng total_quantity
}
