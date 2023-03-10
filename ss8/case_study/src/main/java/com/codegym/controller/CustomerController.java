package com.codegym.controller;

import com.codegym.dto.customer.CustomerDto;
import com.codegym.model.customer.Customer;
import com.codegym.model.customer.CustomerType;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.customer.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping("/list")
    public String list(@PageableDefault(size = 5) Pageable pageable, @RequestParam(value = "name", defaultValue = "") String name,
                       @RequestParam(value = "email", defaultValue = "") String email,
                       @RequestParam(value = "customerType", defaultValue = "") String customerType, Model model) {
        Page<Customer> customers;
        if (customerType.equals("")){
            customers = customerService.listCustomer(name,pageable);
        }else {
            customers = customerService.list(name, email, Integer.parseInt(customerType), pageable);
        }
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("customerType", customerType);
        model.addAttribute("customers", customers);
        return "/customer/list";
    }

    @GetMapping("/create")
    public String formCreateCustomer(Model model) {
        List<CustomerType> customerTypes = customerTypeService.findAll();
        model.addAttribute("customerTypes", customerTypes);
        model.addAttribute("customers", new Customer());
        model.addAttribute("customerDto", new CustomerDto());
        return "/customer/create";
    }

    @PostMapping("/create")
    public String createCustomer(@Validated @ModelAttribute("customerDto") CustomerDto customerDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {
        List<Customer> customers = customerService.findAll();
        if (bindingResult.hasErrors()) {
            List<CustomerType> customerTypes = customerTypeService.findAll();
            model.addAttribute("customerTypes", customerTypes);
            return "/customer/create";
        }
        for (Customer customer1 : customers) {
            if (customerDto.getEmail().equals(customer1.getEmail()) || customerDto.getIdCard().equals(customer1.getIdCard()) || customerDto.getPhoneNumber().equals(customer1.getPhoneNumber())) {
                redirectAttributes.addFlashAttribute("msg", "New add failed");
            } else {
                Customer customer = new Customer();
                BeanUtils.copyProperties(customerDto, customer);
                customerService.save(customer);
                redirectAttributes.addFlashAttribute("msg", "Successfully added new");
            }
            break;
        }
        return "redirect:/customer/list";
    }

    @GetMapping("/edit")
    public String formEditCustomer(@RequestParam("id") int id, Model model) {
        List<CustomerType> customerTypes = customerTypeService.findAll();
        Customer customer = customerService.findById(id);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        model.addAttribute("customerTypes", customerTypes);
        return "/customer/edit";
    }

    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute("customerDto") CustomerDto customerDto, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Update successful");
        return "redirect:/customer/list";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Delete successfully");
        return "redirect:/customer/list";
    }
}
