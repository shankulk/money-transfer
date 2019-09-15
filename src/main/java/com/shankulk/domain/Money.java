package com.shankulk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shankulk.currency.Currency;
import com.shankulk.currency.util.CurrencyConverter;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Money implements Comparable<Money> {

    @Column(name = "AMOUNT")
    public BigDecimal amount;

    @Column(name = "CURRENCY_CODE")
    public Currency currencyCode;

    @Override
    public int compareTo(Money transactedMoney) {
        if(this.currencyCode == transactedMoney.currencyCode) {
            return this.amount.compareTo(transactedMoney.amount);
        }

        Money convertedMoney = CurrencyConverter.convertTo(this.currencyCode, transactedMoney);
        return this.compareTo(convertedMoney);
    }
}
