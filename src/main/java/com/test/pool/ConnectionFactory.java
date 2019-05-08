package com.test.pool;

public enum ConnectionFactory {

    INSTANCE;

    public Connection createConnection(Class classObj) throws PoolException{
        try {
            return (Connection) classObj.newInstance();
        }catch (InstantiationException|IllegalAccessException e){
            throw new PoolException("Failed to instantiate object of "+classObj.getName(),e);
        }

    }

}
