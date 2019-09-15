package com.shankulk.resource;

import com.shankulk.domain.Transaction;
import com.shankulk.dto.TransferRequest;
import com.shankulk.exception.AccountDoesntExistException;
import com.shankulk.exception.InsufficientBalanceException;
import com.shankulk.service.MoneyTransferService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/money")
public class MoneyTransferRoute {

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferRoute(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PUT
    @UnitOfWork
    @Path("/movements/{sourceAccountId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response moveMoney(@PathParam("sourceAccountId") final long sourceAccountId, final TransferRequest request) throws InsufficientBalanceException, AccountDoesntExistException {
        Transaction transaction = moneyTransferService.moveMoneyFrom(sourceAccountId, request);
        return Response.ok().entity(transaction).build();
    }
}
