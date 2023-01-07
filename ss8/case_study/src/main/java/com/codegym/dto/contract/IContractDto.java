package com.codegym.dto.contract;

public interface IContractDto {
    int getId();

    String getStartDate();

    String getEndDate();

    Double getDeposit();

    Double getTotalAmount();

    String getCustomerName();

    String getFacilityName();
}
