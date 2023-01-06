package com.codegym.service.contract;

import com.codegym.dto.contract.IContractDetailDto;
import com.codegym.model.contract.ContractDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractDetailService {
    List<ContractDetail> findAll();

    List<IContractDetailDto> showList(Integer contractId);

    void save(ContractDetail contractDetail);
}
