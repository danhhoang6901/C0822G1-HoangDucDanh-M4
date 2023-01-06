package com.codegym.service.contract.impl;

import com.codegym.dto.contract.IContractDetailDto;
import com.codegym.model.contract.ContractDetail;
import com.codegym.repository.IContractDetailRepository;
import com.codegym.service.contract.IContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractDetailService implements IContractDetailService {
    @Autowired
    private IContractDetailRepository contractDetailRepository;

    @Override
    public List<ContractDetail> findAll() {
        return contractDetailRepository.findAll();
    }

    @Override
    public List<IContractDetailDto> showList(Integer contractId) {
        return contractDetailRepository.showList(contractId);
    }

    @Override
    public void save(ContractDetail contractDetail) {
        contractDetailRepository.save(contractDetail);
    }


}
