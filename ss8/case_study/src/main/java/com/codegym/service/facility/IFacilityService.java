package com.codegym.service.facility;

import com.codegym.model.facility.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFacilityService {
    Page<Facility> list(Pageable pageable, String name, String facilityType);

    void save(Facility facility);

    void remove(int id);

    Facility findById(int id);
}

