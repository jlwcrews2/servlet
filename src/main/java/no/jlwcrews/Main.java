package no.jlwcrews;

import no.jlwcrews.server.CustomerServer;

public class Main {
    public static void main(String[] args) throws Exception {
        new CustomerServer().start();
    }
}