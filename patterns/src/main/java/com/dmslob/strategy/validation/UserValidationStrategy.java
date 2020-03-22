package com.dmslob.strategy.validation;

public class UserValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        if (null == user) {
            return false;
        }
        String userName = user.getUserName();
        return !(userName.isEmpty() || userName.length() < 5);
    }
}
