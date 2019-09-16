package com.shankulk.service;

import com.shankulk.TestFixtures;
import com.shankulk.currency.Currency;
import com.shankulk.dao.AccountsDao;
import com.shankulk.dao.TransactionsDao;
import com.shankulk.domain.Account;
import com.shankulk.domain.Transaction;
import com.shankulk.exception.AccountDoesntExistException;
import com.shankulk.exception.InsufficientBalanceException;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.math.BigDecimal;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static com.shankulk.TestFixtures.DEST_ACC_ID;
import static com.shankulk.TestFixtures.SOURCE_ACC_ID;
import static com.shankulk.TestFixtures.getDestinationAccount;
import static com.shankulk.TestFixtures.getSourceAccount;
import static com.shankulk.currency.Currency.GBP;
import static com.shankulk.currency.Currency.INR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MoneyTransferServiceTest {

    private static AccountsDao accountsDao = Mockito.mock(AccountsDao.class);
    private static TransactionsDao transactionsDao = Mockito.mock(TransactionsDao.class);

    private static MoneyTransferService moneyTransferService = new MoneyTransferService(accountsDao, transactionsDao);

    @ClassRule
    public static final ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule
            .builder()
            .addResource(moneyTransferService)
            .build();

    @BeforeEach
    public void init() {
        Mockito.clearInvocations(accountsDao, transactionsDao);
        Mockito.reset(accountsDao, transactionsDao);
    }

    @Test
    public void testMoveMoney_happyPath() throws InsufficientBalanceException, AccountDoesntExistException {
        when(accountsDao.getById(SOURCE_ACC_ID)).thenAnswer(invocationOnMock -> getSourceAccount(1000, GBP));
        when(accountsDao.getById(DEST_ACC_ID)).thenAnswer(invocationOnMock -> getDestinationAccount(10000, INR));
        when(accountsDao.update(any(Account.class))).thenAnswer(invocationOnMock -> TestFixtures.getDummyAccount(1000, GBP));
        when(transactionsDao.merge(any(Transaction.class))).thenAnswer(invocationOnMock -> TestFixtures.getTransaction());

        Transaction transactionRecord =
                moneyTransferService.moveMoneyFrom(SOURCE_ACC_ID, TestFixtures.getDummyTransferRequest(new BigDecimal(20), GBP));

        assertThat(transactionRecord).isNotNull();
    }

    @Test
    public void testMoveMoney_expectInsufficientBalanceException() throws InsufficientBalanceException, AccountDoesntExistException {
        InsufficientBalanceException insufficientBalanceException = null;
        when(accountsDao.getById(SOURCE_ACC_ID)).thenAnswer(invocationOnMock -> getSourceAccount(1000, GBP));
        when(accountsDao.getById(DEST_ACC_ID)).thenAnswer(invocationOnMock -> getDestinationAccount(10000, INR));
        when(accountsDao.update(any(Account.class))).thenAnswer(invocationOnMock -> TestFixtures.getDummyAccount(1000, GBP));
        when(transactionsDao.merge(any(Transaction.class))).thenAnswer(invocationOnMock -> TestFixtures.getTransaction());

        try {
            moneyTransferService
                    .moveMoneyFrom(SOURCE_ACC_ID, TestFixtures.getDummyTransferRequest(new BigDecimal(20000), GBP));
        } catch (InsufficientBalanceException e) {
            insufficientBalanceException = e;
        } finally {
            assertThat(insufficientBalanceException).isNotNull();
        }
    }

}