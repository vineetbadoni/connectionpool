package com.test.pool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectionPoolTest {

    Pool<DummyConnection> pool = null;

    int capacity = 0;

    @Before
    public void init(){
        capacity = 10;
        //Construct pool.

        pool = new ConnectionPool();

        ConnectionFactory.INSTANCE.createConnection(DummyConnection.class);
    }

    @Test
    public void getConnectionTest(){
        Assert.assertTrue(pool.acquire()!=null);
    }

    @Test
    public void freeConnectionTest(){
        pool.release();
        assert(true);
    }

    @Test(expected = RuntimeException.class)
    public void acquireConnectionMoreThanPoolSize(){
        for(int i=0;i<capacity;i++){
            pool.acquire();
        }

        //By this time all of things are consumed.
        //This should result in error.
        pool.acquire();
    }

    @Test(expected = RuntimeException.class)
    public void freeMoreThantheLimit(){
        for(int i=0;i<capacity;i++){
            pool.acquire();
        }
        //Acquire All the connections
        for(int i=0;i<capacity;i++){
            pool.release();
        }
        //Should throw an exception because we are
        //releasing the extra connections
        pool.release();
    }

}