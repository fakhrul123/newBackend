package com.izak.demoBankManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private LoanType loanType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private Integer tenure; // in months

    @Column(precision = 15, scale = 2)
    private BigDecimal monthlyEmi;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalInterest;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal outstandingBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private LocalDate applicationDate;

    private LocalDate approvalDate;

    private LocalDate disbursementDate;

    private LocalDate maturityDate;

    @Column(length = 50)
    private String approvedBy;

    @Column(length = 500)
    private String purpose;

    @Column(length = 500)
    private String remarks;

    @Column(length = 500)
    private String rejectionReason;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModified;

    // Enums
    public enum LoanType {
        HOME_LOAN,
        CAR_LOAN,
        PERSONAL_LOAN,
        EDUCATION_LOAN,
        BUSINESS_LOAN,
        GOLD_LOAN
    }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        ACTIVE,
        CLOSED,
        DEFAULTED
    }
}
