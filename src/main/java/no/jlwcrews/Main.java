package no.jlwcrews;

import no.jlwcrews.server.CustomerServer;

public class Main {
    public static void main(String[] args) throws Exception {
        //dummy comment to trigger pipeline
        new CustomerServer().start();
    }
}