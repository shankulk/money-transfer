package com.shankulk;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<AppConfig> {

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {

    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {

    }

    @Override
    public String getName() {
        return "MoneyTransferApp";
    }

    public static void main(String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }
}
