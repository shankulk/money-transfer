package com.shankulk;

import com.shankulk.currency.Currency;
import com.shankulk.domain.Account;
import com.shankulk.domain.Money;
import com.shankulk.domain.Transaction;
import com.shankulk.dto.TransferRequest;
import java.math.BigDecimal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestFixtures {


    public static Account getDummyAccount(int i, Currency gbp) {
        Account account = new Account();
        account.accountName = "dummy account";
        account.balance = new Money(new BigDecimal(i), gbp);
        return account;
    }

    public static Transaction getTransaction() {
        return new Transaction(1234l, 3456l, new BigDecimal(2000), Currency.GBP);
    }

    public static TransferRequest getDummyTransferRequest() {
        return new TransferRequest(3456l, new Money(new BigDecimal(2000), Currency.GBP));
    }
}
