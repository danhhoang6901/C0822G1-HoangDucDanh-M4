package com.codegym.repository;

import com.codegym.dto.contract.IContractDto;
import com.codegym.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "SELECT c.id, c.start_date " +
            "as startDate, c.end_date " +
            "as endDate, cu.name " +
            "as customerName, " +
            "f.name " +
            "as facilityName, (sum(ifnull(cd.quantity,0) * ifnull(af.cost,0)) + f.cost) " +
            "AS totalAmount, c.deposit " +
            "FROM `contract` c " +
            "LEFT JOIN contract_detail cd " +
            "ON c.id=cd.contract_id " +
            "LEFT JOIN attach_facility af " +
            "ON cd.attach_facility_id = af.id " +
            "LEFT  JOIN facility f " +
            "ON c.facility_id = f.id " +
            "JOIN customer cu " +
            "ON cu.id = c.customer_id " +
            "WHERE c.status = false " +
            "GROUP BY c.id " +
            "ORDER BY c.id desc",
            nativeQuery = true)
    Page<IContractDto> showList(Pageable pageable);
}
