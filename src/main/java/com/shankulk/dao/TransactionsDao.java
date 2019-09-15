package com.shankulk.dao;

import com.shankulk.domain.Transaction;
import org.hibernate.SessionFactory;

public class TransactionsDao extends BaseDao<Transaction> {

    public TransactionsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
