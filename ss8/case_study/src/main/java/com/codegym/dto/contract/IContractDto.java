package com.codegym.dto.contract;

public interface IContractDto {
    int getId();
    String getFacilityName();
    String getCustomerName();
    String getStartDate();
    String getEndDate();
    double getDeposit();
    double getTotal();
}
