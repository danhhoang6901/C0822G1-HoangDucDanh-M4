package com.codegym.service.customer;

import com.codegym.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Page<Customer> list(String name, String email, String customerType, Pageable pageable);

    void save(Customer customer);

    Customer findById(int id);

    void delete(int id);

    List<Customer> findAll();

    Optional<Customer> findId(int id);

    Page<Customer> showList(String name,String email, int customerId, Pageable pageable);


}
