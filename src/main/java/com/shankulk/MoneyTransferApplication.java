package com.shankulk;

import com.shankulk.dao.AccountsDao;
import com.shankulk.domain.Account;
import com.shankulk.service.AccoutnsService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<AppConfig> {

    private final HibernateBundle<AppConfig> hibernateBundle = new HibernateBundle<AppConfig>(Account.class) {
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
        AccoutnsService accoutnsService = new AccoutnsService(accountsDao);

        environment.jersey().register(accoutnsService);
    }

    @Override
    public String getName() {
        return "MoneyTransferApp";
    }

    public static void main(String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }
}
