package com.shankulk.resource;

import com.shankulk.dao.AccountsDao;
import com.shankulk.domain.Account;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/accounts")
public class AccountsRoute {

    private final AccountsDao accountsDao;

    public AccountsRoute(AccountsDao accountsDao) {
        this.accountsDao = accountsDao;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        return Response.ok(accountsDao.getAll(), MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAccount(Account account) throws URISyntaxException {
        Serializable id = accountsDao.save(account);
        return Response.created(new URI("/accounts/" + id )).build();
    }

    @POST
    @UnitOfWork
    @Path("/batch")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAccounts(List<Account> accounts) throws URISyntaxException {
        for (Account account : accounts) {
            createAccount(account);
        }
        return Response.created(new URI("/accounts/batch")).build();
    }

}
