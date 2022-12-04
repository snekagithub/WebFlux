package com.company.springwebflux.controller;

import com.company.springwebflux.entity.CustomerEntity;
import com.company.springwebflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Mono<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer) {
        return customerService.saveCustomer(customer).log();
    }

    @GetMapping("/{id}")
    public Mono<CustomerEntity> findCustomerById(@PathVariable("id") Long customerId) {
        return customerService.findCustomerById(customerId).log();
    }

    @GetMapping("/customerlist")
    public Flux<CustomerEntity> findCustomerlist() {
        return customerService.findCustomerList().log();
    }

    @PutMapping("/updatecustomer/{id}")
    public Mono<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customer, @PathVariable("id") Long customerId) {
        return customerService.updateCustomer(customer, customerId).log();
    }

//    {
//        return customerService.findCustomerById(customerId).map(cus -> {
//            cus.setName(customer.getName());
//            cus.setPhoneNum(customer.getPhoneNum());
//            cus.setAddress(customer.getAddress());
//            return cus;
//        }).flatMap(cus -> customerService.saveCustomer(cus)).log();

}
