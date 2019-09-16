package com.shankulk.resource;

import com.shankulk.dao.TransactionsDao;
import com.shankulk.domain.Transaction;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static com.shankulk.TestFixtures.getTransaction;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionsRouteTest {

    private static TransactionsDao transactionDao = Mockito.mock(TransactionsDao.class);

    @BeforeEach
    public void init() {
        Mockito.clearInvocations(transactionDao);
    }

    @ClassRule
    public static final ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule
            .builder()
            .addResource(new TransactionsRoute(transactionDao))
            .build();

    @Test
    public void getAllTransactions() {
        Mockito.when(transactionDao.getAll()).thenAnswer(mock -> Arrays.asList(getTransaction()));

        List<Transaction> response = RESOURCE_TEST_RULE.target("/transactions").request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Transaction>>() {});

        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0)).isEqualTo(getTransaction());
    }
}