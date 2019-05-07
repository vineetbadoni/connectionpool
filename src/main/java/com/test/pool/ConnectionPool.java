package com.test.pool;

import java.util.Queue;

public class ConnectionPool<T extends Connection> implements Pool<T> {

    Queue<T> usedPool = null;

    Queue<T> freePool = null;

    private String LOCK = "@@@@LOCK@@@";

    @Override
    public T acquire() {
        //Handle boundaries.
        synchronized (LOCK) {
            if(usedPool.size()==capacity){
                throw new RuntimeException("All connections are used. Try after a while");
            }
            T connection = freePool.poll();
            if(connection==null){//Same as checking free pool size is zero.
                //Create Connection.
                connection = (T)ConnectionFactory.INSTANCE.createConnection();
            }
            usedPool.offer(connection);
            return connection;
        }
    }

    @Override
    public void release() {
        synchronized (LOCK){
            if(usedPool.size()==0){
                throw new RuntimeException("You cannot release without acquiring a connection");
            }
            T connection =usedPool.poll();
            freePool.offer(connection);
        }
    }
}
