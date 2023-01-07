package com.codegym.service.contract;

import com.codegym.dto.contract.IAttachFacilityDto;
import com.codegym.model.contract.AttachFacility;

import java.util.List;

public interface IAttachFacilityService {
    List<IAttachFacilityDto> findAll(Long id);
    List<AttachFacility> findAll();

    AttachFacility findById(int id);
}
