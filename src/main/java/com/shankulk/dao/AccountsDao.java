package com.shankulk.dao;

import org.hibernate.SessionFactory;

public class AccountsDao {

    private final SessionFactory sessionFactory;

    public AccountsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
