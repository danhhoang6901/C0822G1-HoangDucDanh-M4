package com.codegym.service.contract.impl;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import com.codegym.repository.IContractRepository;
import com.codegym.service.contract.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository contractRepository;

    @Override
    public Page<IContractDto> list(Pageable pageable) {
        return contractRepository.list(pageable);
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }
}
