package com.dmslob.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class DateChallenge {

    public static void main(String[] args) {

        List<SpiderMan> spiderManList = Arrays.asList(
                new SpiderMan("The Spider Man", LocalDate.of(1962, Month.SEPTEMBER, 8)),
                new SpiderMan("The Black Suit", LocalDate.of(1984, Month.SEPTEMBER, 8)),
                new SpiderMan("Spider-Man 2099", LocalDate.of(1992, Month.SEPTEMBER, 17))
        );

        LocalDate now = LocalDate.of(2017, Month.SEPTEMBER, 18);

        spiderManList.forEach(
                spiderMan -> {
                    Period period = Period.between(spiderMan.creationDate, now);
                    System.out.println(spiderMan.costume + " " +
                            period.get(ChronoUnit.YEARS) + " years " +
                            period.get(ChronoUnit.DAYS) + " days");
                }
        );
    }

    static class SpiderMan {
        String costume;
        LocalDate creationDate;

        public SpiderMan(String costume, LocalDate creationDate) {
            this.costume = costume;
            this.creationDate = creationDate.plusDays(2).plusYears(2);
        }
    }
}
