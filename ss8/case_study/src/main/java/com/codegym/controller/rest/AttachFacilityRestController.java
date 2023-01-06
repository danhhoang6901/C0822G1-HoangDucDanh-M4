package com.codegym.controller.rest;

import com.codegym.model.contract.AttachFacility;
import com.codegym.service.contract.IAttachFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("attach-facilities")
public class AttachFacilityRestController {
    @Autowired
    private IAttachFacilityService attachFacilityService;

    @GetMapping("")
    public ResponseEntity<List<AttachFacility>> showList() {
        List<AttachFacility> attachFacilities = attachFacilityService.findAll();
        return new ResponseEntity<>(attachFacilities, HttpStatus.OK);
    }
}
