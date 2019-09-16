package com.shankulk.resource;

import com.shankulk.TestFixtures;
import com.shankulk.dto.TransferRequest;
import com.shankulk.exception.AccountDoesntExistException;
import com.shankulk.exception.InsufficientBalanceException;
import com.shankulk.service.MoneyTransferService;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class MoneyTransferRouteTest {

    private static MoneyTransferService moneyTransferService = Mockito.mock(MoneyTransferService.class);

    @BeforeEach
    public void init() {
        Mockito.clearInvocations(moneyTransferService);
    }


    @ClassRule
    public static final ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule
            .builder()
            .addResource(new MoneyTransferRoute(moneyTransferService))
            .build();


    @Test
    public void testMoveMoney() throws InsufficientBalanceException, AccountDoesntExistException {
        when(moneyTransferService.moveMoneyFrom(anyLong(), any(TransferRequest.class))).thenReturn(TestFixtures.getTransaction());

        TransferRequest transferRequest = TestFixtures.getDummyTransferRequest();
        Response response = RESOURCE_TEST_RULE.target("/money/movements/1234").request(MediaType.APPLICATION_JSON)
                .put(Entity.json(transferRequest), Response.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }

}