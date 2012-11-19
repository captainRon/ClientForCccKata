package com.candlelightdoener.xpdaysmathudpclient;

import com.candlelightdoener.xpdaysmathudpclient.client.Client;
import org.apache.log4j.BasicConfigurator;

public class Main {

    public static void main(String args[]) {
        configureLog4J();
        startClient();
    }

    private static void configureLog4J() {
        BasicConfigurator.configure();
    }

    private static void startClient() {
        new Client("localhost", 9001, 9000).run();
    }
}
