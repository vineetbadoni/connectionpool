package com.test.pool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectionPoolTest {

    Pool<DummyConnection> pool = null;

    int capacity = 0;

    @Before
    public void init() {
        capacity = 10;
        //Construct pool.

        pool = new ConnectionPool(DummyConnection.class, capacity);

    }

    @Test
    public void usagePattern() {
        //Get the Connection from the pool.
        DummyConnection connection = pool.acquire();
        //Use the connection to execute. In real example
        //Could be DB connection or a queue connection which will have the exact logic
        //of the task to be performed.
        connection.execute();
        //release on pool which gives the up the connection and puts
        //it back in the system to be re-used.
        pool.release();
        //closes the actual physical connection to the remote system.
        pool.shutdown();
    }

    @Test
    public void getConnectionTest() {
        Assert.assertTrue(pool.acquire() != null);
        pool.shutdown();
    }

    @Test
    public void freeConnectionTest() {
        pool.acquire();
        pool.release();
        pool.shutdown();
        assert (true);
    }

    @Test(expected = PoolException.class)
    public void acquireConnectionMoreThanPoolSize() {
        for (int i = 0; i < capacity; i++) {
            pool.acquire();
        }

        //By this time all of things are consumed.
        //This should result in error.
        pool.acquire();
    }

    @Test(expected = PoolException.class)
    public void freeMoreThantheLimit() {
        for (int i = 0; i < capacity; i++) {
            pool.acquire();
        }
        //Acquire All the connections
        for (int i = 0; i < capacity; i++) {
            pool.release();
        }
        //Should throw an exception because we are
        //releasing the extra connections.
        pool.release();
    }
}