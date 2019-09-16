package com.shankulk.resource;

import com.shankulk.currency.Currency;
import com.shankulk.dao.AccountsDao;
import com.shankulk.domain.Account;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.util.Arrays;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import static com.shankulk.TestFixtures.getDummyAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AccountsRouteTest {

    private static AccountsDao accountsDao = Mockito.mock(AccountsDao.class);

    @ClassRule
    public static final ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule
            .builder()
            .addResource(new AccountsRoute(accountsDao))
            .build();

    @Test
    public void testCreateAccount() {
        when(accountsDao.save(Mockito.any(Account.class))).thenReturn(1l);

        Account account = getDummyAccount(100, Currency.GBP);
        Response response = RESOURCE_TEST_RULE.target("/accounts").request(MediaType.APPLICATION_JSON)
                .post(Entity.json(account), Response.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED_201);
    }

    @Test
    public void testCreateAccounts() {
        when(accountsDao.save(Mockito.any(Account.class))).thenReturn(1l);

        Account account = getDummyAccount(10000, Currency.INR);

        Response response = RESOURCE_TEST_RULE.target("/accounts/batch").request(MediaType.APPLICATION_JSON)
                .post(Entity.json(Arrays.asList(account)), Response.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED_201);
    }

    @Test
    public void testGetAllAccounts() {
        when(accountsDao.getAll()).thenAnswer(mock -> Arrays.asList(getDummyAccount(10000, Currency.INR)));

        Response response = RESOURCE_TEST_RULE.target("/accounts").request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }

}