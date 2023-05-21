package com.example.test.service;

import com.example.test.dto.CustomerCreateDTO;
import com.example.test.dto.CustomerViewDTO;
import com.example.test.models.Customer;
import com.example.test.repository.CustomerRepository;
import com.example.test.serviceImpl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<CustomerViewDTO> getAll() {
        List<CustomerViewDTO> listDto = new ArrayList<>();
        List<Customer> list = customerRepository.findAll();
        for(Customer var: list){
            listDto.add((new CustomerViewDTO(var)));
        }
        return listDto;
    }

    @Override
    public CustomerViewDTO getById(Integer id) {
        if(customerRepository.findById(id).isPresent()){
            Customer customer = customerRepository.findById(id).get();
            return new CustomerViewDTO(customer);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            customerRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(CustomerCreateDTO dto) {
        try{
            Customer customer = new Customer(dto);
            customerRepository.save(customer);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return customerRepository.findCustomerByEmail(email) != null;
    }

    @Override
    public CustomerCreateDTO checkExistPhone(String phone) {
        CustomerCreateDTO dto = new CustomerCreateDTO(customerRepository.findCustomerByPhone(phone));
        return dto;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return customerRepository.findById(id).orElse(null) != null;
    }
    @Override
    public boolean checkExistUsername(String username) {
        return customerRepository.findCustomerByUsername(username) !=null;
    }

    @Override
    public List<CustomerViewDTO> getAllCustomersByPhone(String phone) {
        List<CustomerViewDTO> listDto = new ArrayList<>();
        List<Customer> list = customerRepository.findCustomersByPhone(phone);
        for(Customer var: list){
            listDto.add((new CustomerViewDTO(var)));
        }
        return listDto;
    }


}
