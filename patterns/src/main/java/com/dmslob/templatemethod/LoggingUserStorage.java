package com.dmslob.templatemethod;

import com.dmslob.templatemethod.canonical.User;

public class LoggingUserStorage {

    public long store(User user) {
        System.out.println("User is stored: " + user);
        return System.currentTimeMillis();
    }
}
