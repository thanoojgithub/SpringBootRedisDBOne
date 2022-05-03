package com.selflearnings.springbootredisdb.repository;

import com.selflearnings.springbootredisdb.beans.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {}


