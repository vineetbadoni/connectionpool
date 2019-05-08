package com.test.pool;

public class PoolException extends RuntimeException {

    public PoolException(String reason,Exception e){
        super(reason,e);
    }

    public PoolException(String reason){
        super(reason);
    }
}
