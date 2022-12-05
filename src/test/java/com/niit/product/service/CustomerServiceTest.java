package com.niit.product.service;

import com.niit.product.domain.Customer;
import com.niit.product.domain.Product;
import com.niit.product.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(101, "mobile", "This Is A Product");
        customer = new Customer(1010, "Ashwani", 264325, product);
    }

    @AfterEach
    void tearDown() {
        product = null;
        customer = null;
    }

    @Test
    void saveCustomer() {
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(), customer1.getCustomerId());
        assertNotEquals(20, customer1.getCustomerId());
    }

    @Test
    void getAllCustomerData() {
        List<Customer> customers = customerRepository.findAll();
        assertEquals(0, customers.size());
        assertNotEquals(1, customers.size());
    }

    @Test
    void deleteCustomer() {
        customerRepository.deleteById(customer.getCustomerId());
        assertEquals(Optional.empty(), customerRepository.findById(customer.getCustomerId()));
        assertNotEquals(Optional.of(customer), customerRepository.findById(customer.getCustomerId()));
    }

    @Test
    void getAllCustomerByProductName() {
        List<Customer> customers = customerRepository.findAllCustomerFromProductName(customer.getCustomerProduct().getProductName());
        assertEquals(1, customers.size());
        assertNotEquals(3, customers.size());
        assertEquals(customer.getCustomerProduct().getProductName(), customers.get(0).getCustomerProduct().getProductName());
        assertNotEquals("UnExcepted", customers.get(0).getCustomerProduct().getProductName());
    }
}