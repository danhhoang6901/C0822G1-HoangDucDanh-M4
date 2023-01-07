package com.codegym.repository;

import com.codegym.dto.contract.IAttachFacilityDto;
import com.codegym.model.contract.AttachFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAttachFacilityRepository extends JpaRepository<AttachFacility, Integer> {
    @Query(value = "select af.name as attachName, " +
            "af.cost as cost, " +
            "sum(cd.quantity) " +
            "as totalQuantity, (af.cost * sum(cd.quantity)) " +
            "as totalMoney " +
            "from attach_facility af " +
            "join contract_detail cd " +
            "on af.id = cd.attach_facility_id " +
            "join contract c " +
            "on c.id = cd.contract_id " +
            "where c.id=:id " +
            "group by af.id", nativeQuery = true)
    List<IAttachFacilityDto> findAll(@Param("id") Long id);
}
