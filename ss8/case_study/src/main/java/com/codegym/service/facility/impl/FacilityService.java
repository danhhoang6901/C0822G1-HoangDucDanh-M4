package com.codegym.service.facility.impl;

import com.codegym.model.facility.Facility;
import com.codegym.repository.IFacilityRepository;
import com.codegym.service.facility.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacilityService implements IFacilityService {
    @Autowired
    private IFacilityRepository facilityRepository;

    @Override
    public Page<Facility> list(Pageable pageable, String name, String facilityType) {
        return facilityRepository.list(pageable, name, facilityType);
    }

    @Override
    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void remove(int id) {
        Facility facility = this.findById(id);
        facility.setStatus(true);
        this.facilityRepository.save(facility);
    }

    @Override
    public Facility findById(int id) {
        return facilityRepository.findById(id).orElse(null);
    }
}
