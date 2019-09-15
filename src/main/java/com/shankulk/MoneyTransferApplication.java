package com.shankulk;

import com.shankulk.dao.AccountsDao;
import com.shankulk.dao.TransactionsDao;
import com.shankulk.domain.Account;
import com.shankulk.domain.Transaction;
import com.shankulk.exception.mapper.AccountDoesntExistExceptionMapper;
import com.shankulk.exception.mapper.InsufficientBalanceExceptionMapper;
import com.shankulk.exception.mapper.UnsupportedCurrencyExceptionMapper;
import com.shankulk.resource.AccountsRoute;
import com.shankulk.resource.MoneyTransferRoute;
import com.shankulk.service.MoneyTransferService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<AppConfig> {

    private final HibernateBundle<AppConfig> hibernateBundle = new HibernateBundle<AppConfig>(Account.class,
            Transaction.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) {
        AccountsDao accountsDao = new AccountsDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new AccountsRoute(accountsDao));

        TransactionsDao transactionsDao = new TransactionsDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new MoneyTransferRoute(new MoneyTransferService(accountsDao, transactionsDao)));

        environment.jersey().register(InsufficientBalanceExceptionMapper.class);
        environment.jersey().register(UnsupportedCurrencyExceptionMapper.class);
        environment.jersey().register(AccountDoesntExistExceptionMapper.class);
    }

    @Override
    public String getName() {
        return "MoneyTransferApp";
    }

    public static void main(String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }
}
