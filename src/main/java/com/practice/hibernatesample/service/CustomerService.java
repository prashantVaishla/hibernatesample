package com.practice.hibernatesample.service;

import com.practice.hibernatesample.entities.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Page<Customer> getCustomerPage(int page,int size);
}
