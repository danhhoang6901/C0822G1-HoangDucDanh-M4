package com.codegym.controller.rest;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import com.codegym.model.customer.Customer;
import com.codegym.model.facility.Facility;
import com.codegym.service.contract.IContractService;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.facility.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/contracts")
public class ContractRestController {
    @Autowired
    private IContractService contractService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IFacilityService facilityService;

    @GetMapping("")
    public ResponseEntity<Page<IContractDto>> showList(@PageableDefault(size = 4) Pageable pageable) {
        Page<IContractDto> contractDtos = contractService.showList( pageable);
        return new ResponseEntity<>(contractDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Contract contract) {
        Customer customer = customerService.findById(contract.getCustomer().getId());
        Facility facility = facilityService.findById(contract.getFacility().getId());
        contract.setCustomer(customer);
        contract.setFacility(facility);
        contractService.save(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
