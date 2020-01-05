package com.dmslob.algorithms;

public class UserInput {

    public static class TextInput {
        private StringBuilder value;

        TextInput() {
            this.value = new StringBuilder();
        }

        public void add(char c) {
            this.value.append(c);
        }

        public String getValue() {
            return value.toString();
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            boolean isDigit = Character.isDigit(c);
            if (isDigit) {
                super.add(c);
            }
        }
    }

    public static void main(String[] args) {
        TextInput textInput = new TextInput();
        textInput.add('1');
        textInput.add('a');
        textInput.add('0');
        System.out.println(textInput.getValue());

        TextInput numericInput = new NumericInput();
        numericInput.add('1');
        numericInput.add('a');
        numericInput.add('0');
        System.out.println(numericInput.getValue());
    }
}
