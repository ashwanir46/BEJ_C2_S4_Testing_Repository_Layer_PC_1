package com.niit.product.service;

import com.niit.product.domain.Customer;
import com.niit.product.exception.ProductNotFoundException;
import com.niit.product.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerRepository {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerData() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws ProductNotFoundException {
        boolean result = false;

        if (customerRepository.findById(customerId).isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            customerRepository.deleteById(customerId);
            result = true;
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomerByProductName(String productName) throws ProductNotFoundException {
        if (customerRepository.findAllCustomerFromProductName(productName).isEmpty()) {
            throw new ProductNotFoundException();
        }
        return customerRepository.findAllCustomerFromProductName(productName);
    }
}
