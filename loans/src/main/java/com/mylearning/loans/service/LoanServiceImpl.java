package com.mylearning.loans.service;

import com.mylearning.loans.constants.ApplicationConstant;
import com.mylearning.loans.dto.LoanDto;
import com.mylearning.loans.entity.Loan;
import com.mylearning.loans.exception.LoanAlreadyExistException;
import com.mylearning.loans.exception.ResourceNotFoundException;
import com.mylearning.loans.mapper.LoanMapper;
import com.mylearning.loans.repository.ILoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private ILoanRepository loanRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            throw new LoanAlreadyExistException("Loan Already Exist " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(ApplicationConstant.HOME_LOAN);
        newLoan.setTotalLoan(ApplicationConstant.NEW_LOAN_LIMIT);
        newLoan.setOutstandingAmount(ApplicationConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0L);
        return newLoan;
    }
    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return LoanMapper.toLoanDto(loan, new LoanDto());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */

    @Override
    public boolean updateLoan(LoanDto loansDto) {
        Loan loan = loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(()->new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber()));
        loanRepository.save(LoanMapper.toLoan(loansDto, loan));
        return true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }

}
