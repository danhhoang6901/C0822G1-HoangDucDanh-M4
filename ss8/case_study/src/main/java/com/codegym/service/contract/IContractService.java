package com.codegym.service.contract;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {
    Page<IContractDto> list(Pageable pageable);

    void save(Contract contract);
}
