package com.codegym.repository;

import com.codegym.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c.* from `customer` c  join `customer_type` ct  on c.customer_type_id = ct.id " +
            "where c.name like concat('%', :name, '%') and c.email like concat('%', :email, '%') " +
            "and ct.name like concat('%', :customerType, '%') and c.status = false", nativeQuery = true)
    Page<Customer> list(Pageable pageable,
                        @Param("name") String name,
                        @Param("email") String email,
                        @Param("customerType") String customerType);


    Page<Customer> findByNameContainingAndEmailContaining(String name, String email, Pageable pageable);

    Page<Customer> findByNameContainingAndEmailContainingAndCustomerType(String name, String email, int customerTypeId, Pageable pageable);
}
