package com.shankulk.dao;

import com.shankulk.domain.Account;
import org.hibernate.SessionFactory;

public class AccountsDao extends BaseDao<Account> {

    public AccountsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
