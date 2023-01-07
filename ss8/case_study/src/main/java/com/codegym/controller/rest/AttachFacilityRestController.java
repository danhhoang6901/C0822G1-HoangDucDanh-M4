package com.codegym.controller.rest;

import com.codegym.dto.contract.IAttachFacilityDto;
import com.codegym.model.contract.AttachFacility;
import com.codegym.service.contract.IAttachFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("attach-facilities")
public class AttachFacilityRestController {
    @Autowired
    private IAttachFacilityService attachFacilityService;

    @GetMapping("{id}")
    public ResponseEntity<List<IAttachFacilityDto>> showList(@PathVariable Long id) {
        List<IAttachFacilityDto> attachFacilities = attachFacilityService.findAll(id);
        return new ResponseEntity<>(attachFacilities, HttpStatus.OK);
    }
}
