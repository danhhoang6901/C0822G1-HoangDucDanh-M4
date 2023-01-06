package com.codegym.service.contract;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContractService {

    void save(Contract contract);

    List<Contract> findAll();

    Contract findById(int id);

    Page<IContractDto> showList(Pageable pageable);
}
