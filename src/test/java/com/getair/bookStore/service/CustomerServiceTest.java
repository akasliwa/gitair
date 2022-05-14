package com.getair.bookStore.service;

import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.model.Customer;
import com.getair.bookStore.repository.CustomerRepository;
import com.getair.bookStore.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createCustomer_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer result = customerService.createCustomer(Utils.createCustomer());
        Assert.assertNotNull(result);
    }

    @Test
    public void createCustomer_shouldBeError_customerIsNull() {
        expectedException.expect(BookStoreException.class);
        customerService.createCustomer(null);
    }

    @Test
    public void getCustomerById_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        Customer result = customerService.getCustomerById(Utils.createCustomer().getId());
        Assert.assertNotNull(result);
    }


    @Test
    public void getCustomerById_shouldBeError_customerNotFound() {
        Customer customer = Utils.createCustomer();
        expectedException.expect(BookStoreException.class);
        customerService.getCustomerById(customer.getId());
    }
}
