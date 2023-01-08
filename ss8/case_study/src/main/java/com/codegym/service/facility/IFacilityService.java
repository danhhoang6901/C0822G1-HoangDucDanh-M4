package com.codegym.service.facility;

import com.codegym.model.facility.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFacilityService {
    Page<Facility> list(Pageable pageable, String name, Integer facilityType);

    void save(Facility facility);

    void remove(int id);

    Facility findById(int id);

    List<Facility> findAll();

    Page<Facility> showList(String name, Pageable pageable);
}

