package com.dmslob.generic.constructor;

import java.io.Serializable;

public class RankEntry {
    private String data;
    private int rank;

    public <E extends Rankable & Serializable> RankEntry(E element) {
        this.data = element.toString();
        this.rank = element.getRank();
    }

    public String getData() {
        return data;
    }

    public int getRank() {
        return rank;
    }
}
