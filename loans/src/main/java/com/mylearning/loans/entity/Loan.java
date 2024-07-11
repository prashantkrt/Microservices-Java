package com.mylearning.loans.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;
    @Column(name = "mobile_Number")
    private String mobileNumber;
    @Column(name = "loan_number")
    private String loanNumber;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "total_loan")
    private Long totalLoan;
    @Column(name = "amount_paid")
    private Long amountPaid;
    @Column(name = "outstanding_amount")
    private Long outstandingAmount;
}


