package com.mylearning.loans.service;

import com.mylearning.loans.dto.LoanDto;


public interface ILoanService {
    public void createLoan(String mobileNumber);

    public LoanDto fetchLoan(String mobileNumber);

    public boolean updateLoan(LoanDto loansDto);

    public boolean deleteLoan(String mobileNumber);
}
