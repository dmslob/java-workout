package com.dmslob.algorithms;

public class CCrossWord {

    public static void main(String[] args) {

        CCrossWord crossWord = new CCrossWord();
        String[] words = {"лось", "весна", "вертолет"};
        char[][] chars = crossWord.createCrossWord(words);
        crossWord.show(chars);
    }

    public char[][] createCrossWord(String[] source) throws NullPointerException {
        char[][] word;
        if (source != null) {
            sortArray(source);
            int tempLength = source[0].length();
            word = new char[tempLength + 2][tempLength * 2 + 2];
            for (int i = 0; i < tempLength; i++) {
                word[i + 1][tempLength] = source[0].charAt(i);
            }
            int[] usedIndex = new int[tempLength];
            for (int j = 0; j < tempLength; j += 1) {
                usedIndex[j] = choice(source, source[0].charAt(j), j, word, usedIndex);
            }
        } else {
            throw new NullPointerException("String[] source is null");
        }
        return word;
    }

    private void sortArray(String[] source) {
        String temp;
        for (int i = 0; i < source.length; i++) {
            for (int j = 1; j < source.length; j++) {
                if (source[j].length() > source[j - 1].length()) {
                    temp = source[j - 1];
                    source[j - 1] = source[j];
                    source[j] = temp;
                }
            }
        }
    }

    private int choice(String[] source, char lett, int baseIndex, char[][] word, int[] index) {
        int res = -1;
        for (int i = 1; i < source.length; i++) {
            if (!isContains(index, i) && isChar(source[i], lett)) {
                res = i;
                for (int j = 0; j < source[i].length(); j++) {
                    word[baseIndex + 1][source[0].length() - source[i].indexOf(lett) + j] = source[i].charAt(j);
                }
                return res;
            }
        }
        return res;
    }

    private boolean isContains(int[] arr, int val) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == val) {
                    return true;
                }
            }
            return false;
        } else {
            throw new NullPointerException("int array is null");
        }
    }

    private boolean isChar(String word, char let) {
        return (word.indexOf(let) != -1);
    }

    public void show(char[][] arr) throws NullPointerException {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            throw new NullPointerException("Array is null !!!");
        }
    }
}
