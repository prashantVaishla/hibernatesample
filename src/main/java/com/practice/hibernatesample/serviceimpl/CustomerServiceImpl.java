package com.practice.hibernatesample.serviceimpl;

import com.practice.hibernatesample.dao.CustomerRepository;
import com.practice.hibernatesample.entities.Customer;
import com.practice.hibernatesample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Customer> getCustomerPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return customerRepository.findAll(pageRequest);
    }
}
