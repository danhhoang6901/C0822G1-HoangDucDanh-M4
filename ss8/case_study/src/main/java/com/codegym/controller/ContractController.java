package com.codegym.controller;

import com.codegym.dto.contract.ContractDto;
import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import com.codegym.service.contract.IAttachFacilityService;
import com.codegym.service.contract.IContractDetailService;
import com.codegym.service.contract.IContractService;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.facility.IFacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showListContract(@PageableDefault(size = 4) Pageable pageable, Model model) {
        Page<IContractDto> contracts = contractService.list(pageable);
        model.addAttribute("contracts", contracts);
        model.addAttribute("facilities", facilityService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("contractDetail", contractDetailService.findAll());
        model.addAttribute("attachFacility", attachFacilityService.findAll());
        model.addAttribute("contractDto", new ContractDto());
        model.addAttribute("contract",new Contract());
        return "/contract/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("contract",new Contract());
        model.addAttribute("contractDto", new ContractDto());
        model.addAttribute("facilities", facilityService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("contractDetail", contractDetailService.findAll());
        model.addAttribute("attachFacility", attachFacilityService.findAll());
        return "redirect:/contract/list";
    }

    @PostMapping("/create")
    public String createContract(@ModelAttribute("contractDto") ContractDto contractDto, RedirectAttributes redirectAttributes) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDto, contract);
        contractService.save(contract);
        redirectAttributes.addFlashAttribute("msg", "Successfully added new");
        return "redirect:/contract/list";
    }
}
