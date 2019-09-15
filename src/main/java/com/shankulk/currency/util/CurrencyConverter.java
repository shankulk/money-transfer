package com.shankulk.currency.util;

import com.shankulk.currency.Currency;
import com.shankulk.domain.Money;
import com.shankulk.exception.UnsupportedCurrencyException;
import java.math.BigDecimal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyConverter {

    private static final BigDecimal INR_TO_GBP_FACTOR = new BigDecimal(90);

    public static Money convertTo(Currency destinationCurrencyCode, Money money) {

        // make a third party call for latest currency conversion rates

        if(destinationCurrencyCode == money.currencyCode) {
            return money;
        } else if(destinationCurrencyCode == Currency.GBP) {
            return inrToGbp(money.amount);
        } else if(destinationCurrencyCode == Currency.INR) {
            return gbpToInr(money.amount);
        }

        throw new UnsupportedCurrencyException(destinationCurrencyCode + " is not supported yet.");
    }

    private static Money gbpToInr(BigDecimal amount) {
        return new Money(amount.multiply(INR_TO_GBP_FACTOR), Currency.INR);
    }

    private static Money inrToGbp(BigDecimal amount) {
        return new Money(amount.divide(INR_TO_GBP_FACTOR), Currency.GBP);
    }
}
