package com.test.pool;

public class DummyConnection implements Connection {

    @Override
    public void init() {
        System.out.println("Connection to Mars is initialized");
    }

    @Override
    public void execute() {
        System.out.println("Send this !!!!!@@@@@!!!! to Mars");
    }

    @Override
    public void cleanup() {
        System.out.println("Closing connection to Mars");
    }


}
