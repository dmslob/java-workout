package com.dmslob.templatemethod;

import com.dmslob.templatemethod.canonical.User;

import java.util.StringTokenizer;
import java.util.function.ToLongFunction;

public class ModernUserRestorer {

    private static final String DELIMITER = ":";

    private final ToLongFunction<User> storage;

    public ModernUserRestorer(ToLongFunction<User> storage) {
        this.storage = storage;
    }

    public User restore(String serialized) {
        StringTokenizer tokenizer = new StringTokenizer(serialized, DELIMITER);
        validate(tokenizer);
        User user = parse(tokenizer);
        long id = storage.applyAsLong(user);
        user.setId(id);
        return user;
    }

    private User parse(StringTokenizer tokenizer) {
        String name = tokenizer.nextToken();
        int age = Integer.parseInt(tokenizer.nextToken());
        return new User(name, age);
    }

    private void validate(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() != 2) {
            throw new IllegalArgumentException("Invalid data format!");
        }
    }
}
