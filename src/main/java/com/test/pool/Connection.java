package com.test.pool;

public interface Connection {

    public void init();

    public void execute();

    public void cleanup();

}
