package com.codegym.controller;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import com.codegym.service.contract.IAttachFacilityService;
import com.codegym.service.contract.IContractDetailService;
import com.codegym.service.contract.IContractService;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.facility.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private IContractService contractService;

    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private IAttachFacilityService attachFacilityService;
    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public String showListContract(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<IContractDto> contracts = contractService.list(pageable);
        model.addAttribute("contracts",contracts);
        model.addAttribute("facilities",facilityService.findAll());
        model.addAttribute("customers",customerService.findAll());
        model.addAttribute("contractDetail",contractDetailService.findAll());
        model.addAttribute("attachFacility",attachFacilityService.findAll());
        return "/contract/list";
    }

    @GetMapping("/create")
    public String showFormCreate(){
        return "redirect:/contract/list";
    }

    @PostMapping("/create")
    public String createContract(){}
}
