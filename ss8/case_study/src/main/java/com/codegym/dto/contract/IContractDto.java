package com.codegym.dto.contract;

public interface IContractDto {
    String getStartDate();

    String getEndDate();

    Double getDeposit();

    Double getTotalAmount();

    String getCustomerName();

    String getFacilityName();
}
