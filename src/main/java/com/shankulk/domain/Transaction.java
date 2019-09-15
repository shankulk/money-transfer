package com.shankulk.domain;

import com.shankulk.currency.Currency;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    public Transaction() {
        //Jackson needs no-arg constructor
    }

    public Transaction(long sourceAccountId, long destinationAccountId, BigDecimal amount, Currency currencyCode) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Column(name = "SOURCE_ACC_ID")
    private long sourceAccountId;

    @Column(name = "DEST_ACC_ID")
    private long destinationAccountId;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal amount;

    @Column(name = "TRANSACTION_CURRENCY")
    private Currency currencyCode;

}
