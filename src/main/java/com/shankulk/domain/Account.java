package com.shankulk.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    public long accountId;

    @Column(name = "ACCOUNT_HOLDERS_NAME")
    public String accountName;

    @Embedded
    public Money balance;

    public Account debit(BigDecimal amount) {
        synchronized(this) {
            this.balance.amount = this.balance.amount.subtract(amount);
        }
        return this;
    }

    public Account credit(BigDecimal amount) {
        synchronized(this) {
            this.balance.amount = this.balance.amount.add(amount);
        }
        return this;
    }
}
