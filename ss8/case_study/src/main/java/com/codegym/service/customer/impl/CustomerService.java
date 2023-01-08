package com.codegym.service.customer.impl;

import com.codegym.model.customer.Customer;
import com.codegym.model.customer.CustomerType;
import com.codegym.repository.ICustomerRepository;
import com.codegym.repository.ICustomerTypeRepository;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICustomerTypeRepository customerTypeRepository;

    @Override
    public Page<Customer> list(String name, String email, Integer customerType, Pageable pageable) {
        return customerRepository.list(pageable, name, email, customerType);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        Customer customer = this.findById(id);
        customer.setStatus(true);
        this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findId(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Page<Customer> showList(String name, String email, int customerTypeId, Pageable pageable) {
        Optional<CustomerType> customerType = customerTypeRepository.findById(customerTypeId);
        if (customerTypeId == -1 || customerType.isPresent()) {
            return customerRepository.findByNameContainingAndEmailContaining(name,email,pageable);
        }
        return customerRepository.findByNameContainingAndEmailContainingAndCustomerType(name,email,customerTypeId,pageable);
    }

    @Override
    public Page<Customer> listCustomer(String name, Pageable pageable) {
        return customerRepository.listCustomer(name,pageable);
    }
}
