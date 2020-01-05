package com.dmslob.builder.canonical;

public class BuilderDemo {

    public static void main(String[] args) {

        Cluster cluster = Cluster.runtimeBuilder()
                .addContactPoints("localhost").withPort(3165)
                .withRetryAttempts(3)
                .withoutMetrics().build();

        cluster = Cluster.storingBuilder()
                .addContactPoints("localhost").withPort(3165)
                .withRetryAttempts(3)
                .withoutMetrics().build();
    }
}
