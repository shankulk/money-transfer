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

    public static final long SOURCE_ACC_ID = 1234l;
    public static final long DEST_ACC_ID = 3456l;


    public static Account getDummyAccount(int amount, Currency currency) {
        Account account = new Account();
        account.accountName = "dummy account";
        account.balance = new Money(new BigDecimal(amount), currency);
        return account;
    }

    public static Transaction getTransaction() {
        return new Transaction(1234l, 3456l, new BigDecimal(2000), Currency.GBP);
    }

    public static Transaction getTransaction(int amount, Currency currency) {
        return new Transaction(SOURCE_ACC_ID, DEST_ACC_ID, new BigDecimal(amount), currency);
    }

    public static TransferRequest getDummyTransferRequest() {
        return new TransferRequest(DEST_ACC_ID, new Money(new BigDecimal(20), Currency.GBP));
    }

    public static TransferRequest getDummyTransferRequest(BigDecimal amount, Currency currency) {
        return new TransferRequest(DEST_ACC_ID, new Money(amount, currency));
    }

    public static Account getSourceAccount(int amount, Currency currency) {
        Account account = new Account();
        account.accountId = SOURCE_ACC_ID;
        account.accountName = "source account";
        account.balance = new Money(new BigDecimal(amount), currency);
        return account;
    }

    public static Account getDestinationAccount(int amount, Currency currency) {
        Account account = new Account();
        account.accountId = DEST_ACC_ID;
        account.accountName = "destination account";
        account.balance = new Money(new BigDecimal(amount), currency);
        return account;
    }
}
