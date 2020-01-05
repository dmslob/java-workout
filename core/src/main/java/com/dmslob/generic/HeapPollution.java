package com.dmslob.generic;

import java.util.*;

public class HeapPollution {

    static void testCards() {
        Collection<Suit> suits = Arrays.asList(Suit.values());
        Collection<Rank> ranks = Arrays.asList(Rank.values());

        List<Card> deck = new ArrayList<>();
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); )
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
                deck.add(new Card(i.next(), j.next()));
    }

    public static void main(String[] args) {
        testCards();

        List<String> stringList = new ArrayList<>();
        //List<Object> objectList = stringList; // compile error

        List<String> strings = (List) new ArrayList<Integer>();

        Set s = new TreeSet<Integer>();
        Set<String> ss = s;            // unchecked warning
        s.add(new Integer(42));        // another unchecked warning
        Iterator<String> iter = ss.iterator();
        while (iter.hasNext()) {
            String str = iter.next();   // ClassCastException thrown
            System.out.println(str);
        }

        List<Object> listO = new ArrayList();
        List<String> listS = new ArrayList();

        listO.add("");

        Object io = new Object();
        listS.add((String) io);

        Integer[] jk = new Integer[5];
        jk[0] = (Integer) io;
        Object[] kl = new Object[5];
        kl[0] = new Integer(34);
    }
}

enum Suit {
    CLUB, DIAMOND, HEART, SPADE
}

enum Rank {
    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
    NINE, TEN, JACK, QUEEN, KING
}

class Card {
    Suit suit;
    Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}