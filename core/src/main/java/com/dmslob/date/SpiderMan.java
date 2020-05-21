package com.dmslob.date;

import java.time.LocalDate;

public class SpiderMan {
    private String costume;
    private LocalDate creationDate;

    public SpiderMan(String costume, LocalDate creationDate) {
        this.costume = costume;
        this.creationDate = creationDate.plusDays(2).plusYears(2);
    }

    public String getCostume() {
        return costume;
    }

    public void setCostume(String costume) {
        this.costume = costume;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
