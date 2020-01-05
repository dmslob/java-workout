package com.dmslob.builder;

public class BuilderApplication {

    public static void main(String[] args) {

        Account account = Account.newBuilder()
                .setToken("Token")
                .setUserId("DMSLOB")
                .build();

        System.out.println(account.toString());
    }
}
