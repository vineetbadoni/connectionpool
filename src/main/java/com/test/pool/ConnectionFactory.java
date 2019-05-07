package com.test.pool;

public enum ConnectionFactory {

    INSTANCE;

    public Connection createConnection(Class classObj){
        return new Connection() {
            @Override
            public void init() {
            }

            @Override
            public void execute() {

            }

            @Override
            public void cleanup() {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }

}
