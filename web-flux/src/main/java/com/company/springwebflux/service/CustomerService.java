package com.company.springwebflux.service;

import com.company.springwebflux.entity.CustomerEntity;
import com.company.springwebflux.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Mono<CustomerEntity> saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public Mono<CustomerEntity> findCustomerById(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Flux<CustomerEntity> findCustomerList() {
        return customerRepository.findAll();
    }


    public Mono<CustomerEntity> updateCustomer( CustomerEntity customer, Long customerId) {

        return customerRepository.findById(customerId)
                .flatMap(c -> {
                    c.setName(customer.getName());
                    c.setAddress(customer.getAddress());
                    c.setPhoneNum(customer.getPhoneNum());
                    return customerRepository.save(customer);
                });
//        Mono<CustomerEntity > updateEmployee = customerRepository.findById(customerId);
//
//
//        updateEmployee.setName(customer.getName());
//        updateEmployee.setAddress(customer.getAddress());
//        updateEmployee.setPhoneNum(customer.getPhoneNum());
//
//        return customerRepository.saveAll(updateEmployee);

        //return ResponseEntity.ok(updateEmployee);
    }

    }

