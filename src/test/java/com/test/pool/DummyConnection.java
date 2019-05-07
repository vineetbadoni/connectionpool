package com.test.pool;

public class DummyConnection implements Connection {

    private String name;

    public DummyConnection(String name ){
        this.name = name;
    }

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

    @Override
    public String getName() {
        return name;
    }
}
