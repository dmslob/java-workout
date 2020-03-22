package com.dmslob.strategy.validation;

public class ValidationStrategyDemo {

    public static void main(String[] args) {
        User user = new User("Bobby", "12345678");
        boolean isUserValid = new UserValidationStrategy().validate(user);
        boolean isPasswordValid = new PasswordValidationStrategy().validate(user);
        System.out.println("isUserValid: " + isUserValid);
        System.out.println("isPasswordValid: " + isPasswordValid);
    }
}
