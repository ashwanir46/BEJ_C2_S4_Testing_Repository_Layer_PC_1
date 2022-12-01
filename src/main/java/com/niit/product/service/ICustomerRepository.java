package com.niit.product.service;

import com.niit.product.domain.Customer;
import com.niit.product.exception.ProductNotFoundException;

import java.util.List;

public interface ICustomerRepository {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomerData() throws Exception;

    boolean deleteCustomer(int customerId) throws ProductNotFoundException;

    List<Customer> getAllCustomerByProductName(String productName) throws ProductNotFoundException;
}
