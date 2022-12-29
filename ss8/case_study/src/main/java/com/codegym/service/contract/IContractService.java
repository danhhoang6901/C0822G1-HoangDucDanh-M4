package com.codegym.service.contract;

import com.codegym.dto.contract.IContractDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {
    Page<IContractDto> list(Pageable pageable);
}
