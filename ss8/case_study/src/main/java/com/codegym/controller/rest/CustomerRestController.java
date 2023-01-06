package com.codegym.controller.rest;

import com.codegym.model.customer.Customer;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerRestController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping("")
    public ResponseEntity<Page<Customer>> getList(@RequestParam(defaultValue = "") String customerName, @RequestParam(defaultValue = "") String email,
                                                  @RequestParam(defaultValue = "-1") int customerTypeId, @PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers = customerService.showList(customerName, email, customerTypeId, pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<Page<Customer>> showListBySearch(@PathVariable("search") String searchName,
                                                           @RequestParam(defaultValue = "") String email,
                                                           @RequestParam(defaultValue = "-1") int customerTypeId, @PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers = customerService.showList(searchName, email, customerTypeId, pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> showCustomerById(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.findId(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.findId(id);
        if (customer.isPresent()) {
            customerService.delete(id);
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
