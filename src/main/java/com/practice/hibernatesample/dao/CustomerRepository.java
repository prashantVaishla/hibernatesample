package com.practice.hibernatesample.dao;

import com.practice.hibernatesample.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c from Customer c WHERE c.phone= :phone")
    Customer findCustomerByPhone(@Param("phone") String phone);
}
