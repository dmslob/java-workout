package com.dmslob.algorithms;

import java.util.LinkedList;
import java.util.List;

public class BracketsValidator {

    private final char OPEN_BRACKET = '(';
    private final char CLOSE_BRACKET = ')';

    public static void main(String[] args) {
        BracketsValidator validator = new BracketsValidator();

        String expression = "1+2)/(1+7";
        List<Integer> indicesOfNoValidPositions = validator.check(expression);

        System.out.println(indicesOfNoValidPositions);
    }

    public List<Integer> check(String expression) {

        LinkedList<Character> listChars = new LinkedList<>();
        LinkedList<Integer> listNoValidPositions = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {

            char currentSymbol = expression.charAt(i);
            switch (currentSymbol) {
                case OPEN_BRACKET:
                    listChars.addFirst(currentSymbol);
                    listNoValidPositions.addFirst(i);
                    break;
                case CLOSE_BRACKET:
                    if (!listChars.isEmpty()) {
                        char chClosed = listChars.removeFirst();
                        listNoValidPositions.removeFirst();
                        if (currentSymbol == CLOSE_BRACKET && chClosed != OPEN_BRACKET) {
                            listNoValidPositions.addFirst(i);
                        }
                    } else {
                        listNoValidPositions.addFirst(i);
                    }
                    break;

                default:
                    break;
            }
        }
        return listNoValidPositions;
    }
}
