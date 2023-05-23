package com.example.test.service;

import com.example.test.dto.*;
import com.example.test.models.*;
import com.example.test.repository.*;
import com.example.test.serviceImpl.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.test.common.common.generateString;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderDeliveryRepository orderDeliveryRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderTypeService orderTypeService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    BusinessService businessService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderTagService orderTagService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderTypeRepository orderTypeRepository;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
     ProductDetailRepository productDetailRepository;
    @Autowired
     ProductDetailService productDetailService;
    @Autowired
     ProductService productService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
     TagRepository tagRepository;
    @Autowired
     OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderTime"));
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public List<OrderDTO> getAllByCustomer(Integer customerId) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByCustomerId(customerId);
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    //    @Override
//    public List<OrderDTO> getAllByCondition(Integer employeeId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId) {
//        List<OrderDTO> listDto = new ArrayList<>();
//        Employee employee = employeeService.getById1(employeeId);
//        Optional<Employee> creator =employeeRepository.findById(creatorId);
//        Optional<Business> business = businessRepository.findById(businessId);
//        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
//        Optional<OrderStatus> orderStatus =orderStatusRepository.findById(orderStatusId);
//        Optional<OrderType> orderType = orderTypeRepository.findById(orderTypeId);
//
//        List<Order> list = orderRepository.filterOrderByCondition( employee, creator,  business,  delivery,  orderStatus,  orderType);
//        for(Order var: list){
//            listDto.add((new OrderDTO(var)));
//        }
//        return listDto;
//    }
    @Override
    public List<OrderDTO> getAllByCondition(Integer userId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.filterOrderByCondition(userId, creatorId, businessId, deliveryId, orderStatusId, orderTypeId, orderTimeStart, orderTimeEnd);
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }
    @Override
    public List<OrderDTO> getAllByBusinessId(Integer id) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByBusinessIdOrderByOrderTimeDesc(id);
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }
    @Override
    public OrderDTO getById(Integer id) {
        OrderDTO dto = new OrderDTO();
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).get();
            dto = new OrderDTO(order);
            return dto;
        }
        return null;
    }
    @Override
    public boolean deleteById(Integer id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean save(OrderCreateDTO orderDTO) {
        //nhận dữ liệu từ DTO và ép vào object Order
        Order o = new Order(orderDTO);
        Customer customer = new Customer();
        if (customerRepository.findCustomerByPhone(orderDTO.getPhone()) == null) {
            customer.setFullName(orderDTO.getName());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            customer.setWard(orderDTO.getWard());
            customer.setDistrict(orderDTO.getDistrict());
            customer.setProvince(orderDTO.getProvince());
            customer.setImage("/file/images/shopping.png");
            customer.setStatus(true);
            customer.setSex(true);
            customer.setEmail(orderDTO.getEmail());
            customer.setHeight(orderDTO.getHeight());
            customer.setWeight(orderDTO.getWeight());
            customer.setBirthday(orderDTO.getBirthday());
            customerRepository.save(customer);
        } else {
            customer = customerRepository.findCustomerByPhone(orderDTO.getPhone());
            customer.setFullName(orderDTO.getName()!=null?orderDTO.getName():customer.getFullName());
            customer.setPhone(orderDTO.getPhone()!=null?orderDTO.getPhone():customer.getPhone());
            customer.setAddress(orderDTO.getAddress()!=null?orderDTO.getAddress():customer.getAddress());
            customer.setWard(orderDTO.getWard()!=null?orderDTO.getWard():customer.getWard());
            customer.setDistrict(orderDTO.getDistrict()!=null?orderDTO.getDistrict():customer.getDistrict());
            customer.setProvince(orderDTO.getProvince()!=null?orderDTO.getProvince():customer.getProvince());
            customer.setEmail(orderDTO.getEmail()!=null?orderDTO.getEmail():customer.getEmail());
            customer.setHeight(orderDTO.getHeight()!=null?orderDTO.getHeight():customer.getHeight());
            customer.setWeight(orderDTO.getWeight()!=null?orderDTO.getWeight():customer.getWeight());
            customer.setBirthday(orderDTO.getBirthday()!=null?orderDTO.getBirthday():customer.getBirthday());
            customerRepository.save(customer);
        }
        //liên kết các bảng 1-n
        if(orderDTO.getStatusId()!=null){
            o.setOrderStatus(orderStatusRepository.findById(orderDTO.getStatusId()).orElse(null)); //trạng thái đơn hàng
        }
        if(orderDTO.getTypeId()!=null){
            o.setOrderType(orderTypeRepository.findById(orderDTO.getTypeId()).orElse(null));// kiểu đơn
        }
        if(orderDTO.getBusinessId()!=null){
            o.setBusiness(businessRepository.findById(orderDTO.getBusinessId()).orElse(null)); // business
        }
        if(orderDTO.getDeliveryId()!=null){
            o.setDelivery(deliveryRepository.findById(orderDTO.getDeliveryId()).orElse(null));//đơn vị vận chuyển
        }
        if(orderDTO.getUserId()!=null){
            o.setUser(userRepository.findById(orderDTO.getUserId()).orElse(null));//đơn của nhân viên A
        }
        if(orderDTO.getCreatorId()!=null){
            o.setUser1(userRepository.findById(orderDTO.getCreatorId()).orElse(null));//người tạo đơn của nhân viên A
        }
        if(orderDTO.getId()==null){
            //tao moi
            o.setBillCode(generateString(o.getBusiness().getCodeName().trim())); //taoj mã bill code
        }else {
            o.setBillCode(getById(orderDTO.getId()).getBillCode());
        }

        o.setCustomer(customer);
        if (o.getOrderTime() == null) {
            o.setOrderTime(new Date());
            o.setCustomer(customer);
        }
        orderRepository.save(o);
        createOrderTag(orderDTO, o);
        createProductDetail(orderDTO, o);
        return true;
    }
    public void createProductDetail(@RequestBody OrderCreateDTO orderDTO, Order o){
        orderDTO.getOrderDetailDTO().forEach(var -> {
            ProductDetail productDetail = productDetailRepository.findById(var.getProDeId()).orElse(null);
            productService.handleWhenCreateOrder(var.getProDeId(),productDetail);
            if (productDetail != null) {
                OrderDetail orderDetail = new OrderDetail(var);
                orderDetail.setOrders(o);
                orderDetail.setProductDetail(productDetail);
                orderDetail.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                orderDetailService.save(orderDetail);
            }
        });
    }
    public void createOrderTag(@RequestBody OrderCreateDTO orderCreateDTO, Order p) {
        if(orderCreateDTO.getOrderTag()!= null){
            if(orderTagService.deleteOrderTagByOrderId(p.getId())){
                orderCreateDTO.getOrderTag().forEach(var -> {
                    Tag tag = new Tag();
                    tag = tagRepository.findById(var.getTagId()).orElse(null);
                    if (tag!=null){
                        OrderTag orderTag = new OrderTag();
                        orderTag.setOrder(p);
                        orderTag.setTag(tag);
                        orderTagService.save(orderTag);
                    }
                });
            }else{
                System.out.println("NG");
            }

        }
    }
    @Override
    public boolean updateStatus(Integer id, Integer statusId) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o != null) {
            o.setOrderStatus(orderStatusRepository.findById(statusId).orElse(null));
            orderRepository.save(o);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateDelivery(Integer id, Integer deliveryId) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o != null) {
            o.setDelivery(deliveryRepository.findById(deliveryId).orElse(null));
            orderRepository.save(o);
            return true;
        }
        return false;
    }
    @Override
    public List<OrderDTO> getOrderByStatus(Integer id) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByOrderStatusId(id);
        for (Order var : list) {
            listDto.add(new OrderDTO(var));
        }
        return listDto;
    }
    @Override
    public OrderDTO printBill(Integer id, Integer deliveryId) {
        if(updateDelivery(id, deliveryId)){
            if(updateStatus(id, 3)){
                Order o = orderRepository.findById(id).orElse(null);
                return new OrderDTO(o);
            }
            return null;
        }
        return null;
    }
    @Override
    public List<OrderDTO> printMultipleBill(OrderPrintMultipleDTO orderPrintMultipleDTO) {
        List<OrderDTO> listDto = new ArrayList<>();
        orderPrintMultipleDTO.getListOrder().forEach(var->{
            OrderDTO orderDTO = printBill((Integer) var, orderPrintMultipleDTO.getDeliveryId());
            listDto.add(orderDTO);
        });
        return listDto;
    }
    @Override
    public OrderDTO getOrderByBillCode(String billCode) {
        Order o = orderRepository.findOrderByBillCode(billCode);
        if(o!=null){
            return new OrderDTO(o);
        }
        return null;
    }
    @Override
    public List<OrderDTO> findOrderByPhoneOrBillCode(String phone, String billCode ) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByPhoneOrBillCode(phone, billCode);
        for (Order var : list) {
            listDto.add(new OrderDTO(var));
        }
        return listDto;
    }
    @Override
    public boolean updateOrderStatusAndReFundProductDetail(Integer id) {
        Order o = orderRepository.findById(id).orElse(null);

        o.getOrderDetail().forEach(var->{
            ProductDetail pd = new ProductDetail(var.getProductDetail());
            productService.handleWhenReFundOrder(var.getQuantity(), pd);
        });
        if (o != null) {
            o.setOrderStatus(orderStatusRepository.findById(8).orElse(null));
            orderRepository.save(o);
            return true;
        }
        return false;
    }
    @Override
    public boolean scanTrackingOrderAndChangeQuantityHold(String billCode) {
        try{
            OrderDTO dto = getOrderByBillCode(billCode);
            dto.getOrderDetail().forEach(var->{
                ProductDetail pd = productDetailRepository.findById(var.getProDeId()).orElse(null);
                productService.handleWhenPrintBillOrder(var.getQuantity(), pd);
            });
            updateStatus(dto.getId(), 4);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
public void createOrderByPosCake(String orderPosCake) throws JsonProcessingException, ParseException {
        //gson
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(orderPosCake, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        //JsonPath
        DocumentContext jsonContext = JsonPath.parse(orderPosCake);
        OrderDTO orderDTO = getOrderByBillCode(jsonContext.read("$.id"));//check billCode đã tồn tại hay chưa và loại webhook
        if (orderDTO==null) {
            //xử lý customer
            Customer customer = new Customer();
            if (customerRepository.findCustomerByPhone(jsonContext.read("$.shipping_address.phone_number")) == null) {
                customer.setFullName(jsonContext.read("$.shipping_address.full_name"));
                customer.setPhone(jsonContext.read("$.shipping_address.phone_number"));
                customer.setAddress(jsonContext.read("$.shipping_address.address"));
                customer.setWard(jsonContext.read("$.shipping_address.commnue_name"));
                customer.setDistrict(jsonContext.read("$.shipping_address.district_name"));
                customer.setProvince(jsonContext.read("$.shipping_address.province_name"));
                customer.setImage("/file/images/shopping.png");
                customer.setStatus(true);
                if(jsonContext.read("$.customer.gender")!=null) {
                    String gender = jsonContext.read("$.customer.gender");
                    customer.setSex(gender.equals("male") ? true : false);
                }
                customer.setBirthday(jsonContext.read("$.customer.date_of_birth"));
                if (jsonObject.has("customer")) {
                    JsonObject customerObject = jsonObject.getAsJsonObject("customer");
                    JsonArray emailsArray = customerObject.getAsJsonArray("emails");
                    if (emailsArray.size() > 0) {
                        String firstEmail = emailsArray.get(0).getAsString();
                        customer.setEmail(firstEmail);
                    }
                }
                customerRepository.save(customer);
            }else {
                customer=customerRepository.findCustomerByPhone(jsonContext.read("$.shipping_address.phone_number"));
            }
            //xử lý chuỗi string json ra từng thuộc tính
            Integer shipping_fee = jsonContext.read("$.shipping_fee");
            Integer productMoney = jsonContext.read("$.total_price");
            Integer paid = jsonContext.read("$.transfer_money");
            Integer discount = jsonContext.read("$.total_discount");
            Integer totalMoney = productMoney+shipping_fee-discount;
            Integer totalAmount = jsonContext.read("$.cod");

            Order o = new Order();
            //xử lý order status
            o.setOrderStatus(orderStatusRepository.findById(2).orElse(null)); //trạng thái đơn hàng
            //xử lý order type
            o.setOrderType(orderTypeRepository.findById(1).orElse(null));// kiểu đơn
            //Xử lý business
            BusinessDTO businessDTO = businessService.findBusinessByPageId(jsonContext.read("$.page_id"));
            Business business = new Business(businessDTO);
            o.setBusiness(business); // business
            //xử lý delivery
            o.setDelivery(deliveryRepository.findById(6).orElse(null));//đơn vị vận chuyển
            //xử lý người bán hàng
            o.setUser(userRepository.findById(1).orElse(null));//đơn của nhân viên A
            //xử lý người tạo đơn
            o.setUser1(userRepository.findById(1).orElse(null));//người tạo đơn của nhân viên A

            o.setName(jsonContext.read("$.shipping_address.full_name"));
            o.setPhone(jsonContext.read("$.shipping_address.phone_number"));
            o.setAddress(jsonContext.read("$.shipping_address.full_address"));
            o.setBillCode(jsonContext.read("$.id"));
            o.setDiscount(Double.valueOf(discount));
            o.setProductMoney(Double.valueOf(productMoney));
            o.setPaid(Double.valueOf(paid));
            o.setShippingPrice(Double.valueOf(shipping_fee));
            o.setTotalMoney(Double.valueOf(totalMoney));
            o.setPaymentAmount(Double.valueOf(totalAmount));
            o.setInternalNotes(jsonContext.read("$.note"));
            o.setShippingNotes(jsonContext.read("$.note_print"));
            o.setProvince(jsonContext.read("$.shipping_address.province_name"));
            o.setDistrict(jsonContext.read("$.shipping_address.district_name"));
            o.setWard(jsonContext.read("$.shipping_address.commnue_name"));
            o.setValue(orderPosCake);
            o.setCustomer(customer);
            orderRepository.save(o);
            createProductDetailByPosCake(orderPosCake, o);
        } else if (orderDTO!=null) {
            //cập nhật customer của đơn cần update
            Customer customer = new Customer();
            customer = customerRepository.findCustomerByPhone(jsonContext.read("$.shipping_address.phone_number"));
            customer.setFullName(jsonContext.read("$.shipping_address.full_name") != null ?jsonContext.read("$.shipping_address.full_name") : customer.getFullName());
            customer.setPhone(jsonContext.read("$.shipping_address.phone_number") != null ?jsonContext.read("$.shipping_address.phone_number") : customer.getPhone());
            customer.setAddress(jsonContext.read("$.shipping_address.address") != null ?jsonContext.read("$.shipping_address.address") : customer.getAddress());
            customer.setWard(jsonContext.read("$.shipping_address.commnue_name") != null ?jsonContext.read("$.shipping_address.commnue_name") : customer.getWard());
            customer.setDistrict(jsonContext.read("$.shipping_address.district_name") != null ?jsonContext.read("$.shipping_address.district_name") : customer.getDistrict());
            customer.setProvince(jsonContext.read("$.shipping_address.province_name") != null ?jsonContext.read("$.shipping_address.province_name") : customer.getProvince());

            if (jsonObject.has("customer")) {
                JsonObject customerObject = jsonObject.getAsJsonObject("customer");
                JsonArray emailsArray = customerObject.getAsJsonArray("emails");
                if (emailsArray.size() > 0) {
                    String firstEmail = emailsArray.get(0).getAsString();
                    customer.setEmail(firstEmail);
                }
            }
            customerRepository.save(customer);

            //xử lý chuỗi string json ra từng thuộc tính
            Integer shipping_fee = jsonContext.read("$.shipping_fee");
            Integer productMoney = jsonContext.read("$.total_price");
            Integer paid = jsonContext.read("$.transfer_money");
            Integer discount = jsonContext.read("$.total_discount");
            Integer totalMoney = productMoney + shipping_fee - discount;
            Integer totalAmount = jsonContext.read("$.cod");
            Order o = new Order(orderDTO);
            //xử lý order status + delivery
            JsonElement partner = jsonObject.get("partner");
            if (partner.isJsonObject() && !partner.isJsonNull()) {
                JsonObject partnerObject = jsonObject.getAsJsonObject("partner");
                String partnerStatus = jsonContext.read("$.partner.partner_status");
                //status
                if (partnerStatus != null) {
                    Integer orderStatusId = handleChangeOrderStatus(jsonContext.read("$.partner.partner_status"));
                    OrderStatusDTO orderStatusDTO = orderStatusService.getById(orderStatusId);
                    OrderStatus orderStatus = new OrderStatus(orderStatusDTO);
                    o.setOrderStatus(orderStatus); //trạng thái đơn hàng
                } else {
                    OrderStatus orderStatus = new OrderStatus(orderStatusService.getById(3));
                    o.setOrderStatus(orderStatus);
                }
                //xử lý delivery
                if (partnerObject.has("partner_name")) {
                    String partnerName = jsonContext.read("$.partner.partner_name");
                    DeliveryDTO deliveryDTO = deliveryService.findByName(partnerName);
                    Delivery delivery = new Delivery(deliveryDTO);
                    o.setDelivery(delivery); //đơn vị vận chuyển
                }
            } else {
                //xử lý status order khi ko tìm thấy part_name
                OrderStatus orderStatus = new OrderStatus(orderStatusService.getById(orderDTO.getOrderStatusDTO().getId()));
                o.setOrderStatus(orderStatus);
                //xử lý delivery khi ko tìm thấy part_name
                Delivery delivery = new Delivery(deliveryService.getById(orderDTO.getDeliveryDTO().getId()));
                o.setDelivery(delivery);
            }
            User user = new User(userService.getById(1));
            //xử lý người bán hàng
            o.setUser(user);
            //xử lý người tạo đơn
            o.setUser1(user);
            //xử lý order type
            OrderType orderType = new OrderType(orderTypeService.getById(1));
            o.setOrderType(orderType);// kiểu đơn
            //Xử lý business
            BusinessDTO businessDTO = businessService.findBusinessByPageId(jsonContext.read("$.page_id"));
            Business business = new Business(businessDTO);
            o.setBusiness(business); // business
            o.setName(jsonContext.read("$.shipping_address.full_name"));
            o.setPhone(jsonContext.read("$.shipping_address.phone_number"));
            o.setAddress(jsonContext.read("$.shipping_address.address"));
            o.setDiscount(Double.valueOf(discount));
            o.setProductMoney(Double.valueOf(productMoney));
            o.setPaid(Double.valueOf(paid));
            o.setShippingPrice(Double.valueOf(shipping_fee));
            o.setTotalMoney(Double.valueOf(totalMoney));
            o.setPaymentAmount(Double.valueOf(totalAmount));
            o.setInternalNotes(jsonContext.read("$.note"));
            o.setShippingNotes(jsonContext.read("$.note_print"));
            o.setProvince(jsonContext.read("$.shipping_address.province_name"));
            o.setDistrict(jsonContext.read("$.shipping_address.district_name"));
            o.setWard(jsonContext.read("$.shipping_address.commnue_name"));
            o.setValue(orderPosCake);
            o.setCustomer(customer);

            orderRepository.save(o);
            updateProductDetailByPosCake(orderPosCake, o);
            if (partner.isJsonObject() && !partner.isJsonNull()) {
                if(orderDeliveryRepository.findOrderDeliveryByCodeDelivery(jsonContext.read("$.partner.extend_code"))==null){
                    OrderDelivery orderDelivery = new OrderDelivery();
                    orderDelivery.setOrder(o);
                    orderDelivery.setStatus(true);
                    orderDelivery.setDelivery(new Delivery(deliveryService.findByName(jsonContext.read("$.partner.partner_name"))));
                    orderDelivery.setCodeDelivery(jsonContext.read("$.partner.extend_code"));
                    orderDeliveryRepository.save(orderDelivery);
                }
            }
        }else{
            System.out.println("NG");
        }
    }
    public void createProductDetailByPosCake(String orderPosCake, Order o){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(orderPosCake, JsonElement.class);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");
            for (JsonElement itemElement : itemsArray) {
                JsonObject itemObject = itemElement.getAsJsonObject();
                Integer quantity = itemObject.get("quantity").getAsInt();
                JsonObject variationInfoObject = itemObject.getAsJsonObject("variation_info");
                String displayId = variationInfoObject.get("display_id").getAsString();
                Integer retailPrice = variationInfoObject.get("retail_price").getAsInt();
                ProductDetailDTO dto = productDetailService.findProductDetailByCodeName(displayId);
                ProductDetail pd = new ProductDetail(dto);
                if(pd!=null){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductDetail(pd);
                    orderDetail.setOrders(o);
                    orderDetail.setQuantity(quantity);
                    orderDetail.setPrice(Float.valueOf(retailPrice));
                    orderDetail.setDiscount(Float.valueOf(0));
                    orderDetailService.save(orderDetail);
                }
            }
        }
    }
    public void updateProductDetailByPosCake(String orderPosCake, Order o){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(orderPosCake, JsonElement.class);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");
            for (JsonElement itemElement : itemsArray) {
                JsonObject itemObject = itemElement.getAsJsonObject();
                Integer quantity = itemObject.get("quantity").getAsInt();
                JsonObject variationInfoObject = itemObject.getAsJsonObject("variation_info");
                String displayId = variationInfoObject.get("display_id").getAsString();
                Integer retailPrice = variationInfoObject.get("retail_price").getAsInt();
                ProductDetailDTO dto = productDetailService.findProductDetailByCodeName(displayId);
                ProductDetail pd = new ProductDetail(dto);
                if(pd!=null){
                    OrderDetailDTO orderDetailDTO = orderDetailService.findbyOrderIdAndProductDetailId(pd.getId(),o.getId());
                    OrderDetail orderDetail = new OrderDetail(orderDetailDTO);
                    orderDetail.setProductDetail(pd);
                    orderDetail.setOrders(o);
                    orderDetail.setQuantity(quantity);
                    orderDetail.setPrice(Float.valueOf(retailPrice));
                    orderDetail.setDiscount(Float.valueOf(0));
                    orderDetailService.save(orderDetail);
                }
            }
        }
    }
    public Integer handleChangeOrderStatus(String partnerStatus){
        Integer orderStatusId=0;
        if(partnerStatus.equals("request_received")){
            orderStatusId=2;
        }else if(partnerStatus.equals("delay_pickup")){
            orderStatusId=20;
        }else if(partnerStatus.equals("picking_up")){
            orderStatusId=11;
        }else if(partnerStatus.equals("picked_up")){
            orderStatusId=4;
        }else if(partnerStatus.equals("on_the_way")){
            orderStatusId=12;
        }else if(partnerStatus.equals("delay_delivery")){
            orderStatusId=13;
        }else if(partnerStatus.equals("out_for_delivery")){
            orderStatusId=14;
        }else if(partnerStatus.equals("waiting_for_return")){
            orderStatusId=16;
        }else if(partnerStatus.equals("delivered")){
            orderStatusId=17;
        }else if(partnerStatus.equals("delivered_cod")){
            orderStatusId=6;
        }else if(partnerStatus.equals("returning")){
            orderStatusId=7;
        }else if(partnerStatus.equals("returned")){
            orderStatusId=8;
        }else if(partnerStatus.equals("returned_cod")){
            orderStatusId=18;
        }else if(partnerStatus.equals("canceled")){
            orderStatusId=10;
        }else{
            orderStatusId=19;
        }

        return orderStatusId;
    }
}
