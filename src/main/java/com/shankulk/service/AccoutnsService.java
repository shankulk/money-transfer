package com.shankulk.service;

import com.shankulk.dao.AccountsDao;

public class AccoutnsService {

    private final AccountsDao accountsDao;

    public AccoutnsService(AccountsDao accountsDao) {
        this.accountsDao = accountsDao;
    }
}
