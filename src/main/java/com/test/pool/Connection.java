package com.test.pool;

public interface Connection {

    String name = null;

    public void init();

    public void execute();

    public void cleanup();

    public String getName();
}
