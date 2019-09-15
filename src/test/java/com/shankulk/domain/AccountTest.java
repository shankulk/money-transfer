package com.shankulk.domain;

import com.shankulk.currency.Currency;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountTest {

    @Test
    public void testCredit() {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(new Money(new BigDecimal(90), Currency.INR));
        assertEquals(90, sourceAccount.getBalance().amount);
    }

}