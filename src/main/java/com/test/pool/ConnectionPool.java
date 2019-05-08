package com.test.pool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool<T extends Connection> implements Pool<T> {

    Queue<T> usedPool = null;

    Queue<T> freePool = null;

    Class<T> connectionObj = null;

    int capacity=0;

    public ConnectionPool(Class<T> tClass,int size){
        this.connectionObj = tClass;
        this.capacity = size;
        usedPool = new LinkedBlockingQueue<T>(capacity);
        freePool = new LinkedBlockingQueue<T>(capacity);
    }

    private String LOCK = "@@@@LOCK@@@";

    @Override
    public T acquire() throws PoolException{
        //Handle boundaries.
        synchronized (LOCK) {
            if(usedPool.size()==capacity){
                throw new PoolException("All connections are used. Try after a while");
            }
            T connection = freePool.poll();
            if(connection==null){//Same as checking free pool size is zero.
                //Create Connection.
                connection = (T)ConnectionFactory.INSTANCE.createConnection(connectionObj);
                connection.init();
            }
            usedPool.offer(connection);
            return connection;
        }
    }

    @Override
    public void release() throws PoolException{
        synchronized (LOCK){
            if(usedPool.size()==0){
                throw new PoolException("You cannot release without acquiring a connection");
            }
            T connection =usedPool.poll();
            freePool.offer(connection);
        }
    }

    @Override
    public void shutdown() throws PoolException {
        //Get All connections from usedPool.
        T connection ;
        while((connection = usedPool.poll())!=null){
            connection.cleanup();
        }

        //Get All connections from freePool.
        while((connection = freePool.poll())!=null){
            connection.cleanup();
        }
    }
}
