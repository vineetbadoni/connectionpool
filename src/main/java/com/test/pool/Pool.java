package com.test.pool;

public interface Pool<T extends Connection>
{

    T acquire() throws PoolException;

    void release() throws PoolException;

    void shutdown() throws PoolException;

}
