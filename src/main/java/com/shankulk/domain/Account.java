package com.shankulk.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
