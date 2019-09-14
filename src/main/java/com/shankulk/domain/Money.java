package com.shankulk.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Money {

    @Column(name = "AMOUNT")
    public BigDecimal amount;

    @Column(name = "CURRENCY")
    public String currencyCode;
}
