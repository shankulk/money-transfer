package com.shankulk.resource;

import com.shankulk.dao.TransactionsDao;
import com.shankulk.domain.Transaction;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transactions")
public class TransactionsRoute {

    private final TransactionsDao transactionsDao;

    public TransactionsRoute(TransactionsDao transactionsDao) {
        this.transactionsDao = transactionsDao;
    }

    @GET
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transaction> getAllTransactions() {
        return transactionsDao.getAll();
    }
}
