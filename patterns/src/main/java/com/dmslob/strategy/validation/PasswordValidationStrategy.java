package com.dmslob.strategy.validation;

public class PasswordValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        if (null == user) {
            return false;
        }
        String userPassword = user.getPassword();
        return !(userPassword.isEmpty() || userPassword.length() < 8);
    }
}
