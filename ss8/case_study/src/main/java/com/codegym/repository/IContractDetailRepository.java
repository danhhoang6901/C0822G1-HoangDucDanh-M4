package com.codegym.repository;

import com.codegym.dto.contract.IContractDetailDto;
import com.codegym.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractDetailRepository extends JpaRepository<ContractDetail, Integer> {
    @Query(value = "SELECT cd.id, cd.contract_id " +
            "as contractId, af.name " +
            "as attachFacilityName, cd.quantity " +
            "FROM contract_detail cd " +
            "JOIN attach_facility af " +
            "on cd.attach_facility_id = af.id " +
            "WHERE cd.contract_id = :id",nativeQuery = true)
    List<IContractDetailDto> showList(@Param("id") Integer contractId);
}
