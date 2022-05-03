package com.selflearnings.springbootredisdb.service;

import com.selflearnings.springbootredisdb.beans.Customer;
import com.selflearnings.springbootredisdb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    public String addCustomer(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    public Optional<Customer> getCustomer(String id) {
        return customerRepository.findById(id);
    }
}
