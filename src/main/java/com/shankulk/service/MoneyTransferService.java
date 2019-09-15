package com.shankulk.service;

import com.shankulk.dao.AccountsDao;
import com.shankulk.dao.TransactionsDao;
import com.shankulk.domain.Account;
import com.shankulk.domain.Money;
import com.shankulk.domain.Transaction;
import com.shankulk.dto.TransferRequest;
import com.shankulk.exception.AccountDoesntExistException;
import com.shankulk.exception.InsufficientBalanceException;

import static com.shankulk.currency.util.CurrencyConverter.convertTo;

public class MoneyTransferService {
    private final AccountsDao accountDao;
    private final TransactionsDao transactionsDao;

    public MoneyTransferService(AccountsDao accountDao, TransactionsDao transactionsDao) {
        this.accountDao = accountDao;
        this.transactionsDao = transactionsDao;
    }

    public synchronized Transaction moveMoneyFrom(long sourceAccountId, TransferRequest request) throws InsufficientBalanceException, AccountDoesntExistException {
        Account sourceAccount = getAccount(sourceAccountId);
        if (null == sourceAccount) {
            throw new AccountDoesntExistException("Account " + sourceAccountId + " does not exist");
        }
        checkForSufficientBalance(sourceAccount.balance, request.money);
        debitSourceAccount(request, sourceAccount);

        Account destinationAccount = getAccount(request.destinationAccountId);
        if (null == destinationAccount) {
            throw new AccountDoesntExistException("Account " + request.destinationAccountId + " does not exist");
        }
        creditDestinationAccount(request, destinationAccount);

        return createTransactionRecord(sourceAccountId, request);

    }

    private Transaction createTransactionRecord(long sourceAccountId, TransferRequest request) {
        Transaction transaction = new Transaction(sourceAccountId, request.destinationAccountId, request.money.amount, request.money.currencyCode);
        return transactionsDao.merge(transaction);
    }

    private void creditDestinationAccount(TransferRequest request, Account destinationAccount) {
        Account updatedDestinationAccount = destinationAccount.credit(convertTo(destinationAccount.getBalance().getCurrencyCode(),request.money).amount);
        accountDao.update(updatedDestinationAccount);
    }

    private void debitSourceAccount(TransferRequest request, Account sourceAccount) {
        Account updatedSourceAccount = sourceAccount.debit(convertTo(sourceAccount.getBalance().getCurrencyCode(), request.money).amount);
        accountDao.update(updatedSourceAccount);
    }

    private Account getAccount(long sourceAccountId) {
        return accountDao.getById(sourceAccountId);
    }

    private void checkForSufficientBalance(Money sourceAccountBalance, Money transactedMoney) throws InsufficientBalanceException {
        if(sourceAccountBalanceMoreThanOrEqualToTransaction(sourceAccountBalance, transactedMoney))
            throw new InsufficientBalanceException("There is no sufficient balance in your account.");

    }

    private boolean sourceAccountBalanceMoreThanOrEqualToTransaction(Money sourceAccountBalance, Money transactedMoney) {
        return sourceAccountBalance.compareTo(transactedMoney) < 0;
    }
}
