package com.test.pool;

public interface Pool<T extends Connection>
{

    int capacity=0;

    public T acquire();

    public void release();

}
