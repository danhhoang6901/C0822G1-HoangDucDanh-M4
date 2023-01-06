package com.codegym.controller.rest;

import com.codegym.dto.contract.IContractDetailDto;
import com.codegym.model.contract.AttachFacility;
import com.codegym.model.contract.Contract;
import com.codegym.model.contract.ContractDetail;
import com.codegym.service.contract.IAttachFacilityService;
import com.codegym.service.contract.IContractDetailService;
import com.codegym.service.contract.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("contract-details")
public class ContractDetailRestController {
    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IAttachFacilityService attachFacilityService;

    @GetMapping("")
    public ResponseEntity<List<IContractDetailDto>> showList(@PathVariable("id") int contractId) {
        List<IContractDetailDto> contractDetailDtos = contractDetailService.showList(contractId);
        return new ResponseEntity<>(contractDetailDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ContractDetail contractDetail) {
        Contract contract = contractService.findById(contractDetail.getContract().getId());
        AttachFacility attachFacility = attachFacilityService.findById(contractDetail.getAttachFacility().getId());
        contractDetail.setContract(contract);
        contractDetail.setAttachFacility(attachFacility);
        contractDetailService.save(contractDetail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
