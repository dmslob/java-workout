package com.dmslob;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");
        Optional<String> name1 = Optional.empty();
        name.isPresent();
        System.out.println(name1.get());

        Optional.of("John").orElse("hello");

        USB usb = new USB();
        SoundCard soundcard = new SoundCard(usb);
        Computer computer = new Computer(soundcard);
        System.out.println(computer.getSoundCard().getUSB().getVersion());
    }
}

class Computer {
    private Optional<SoundCard> soundCard;

    public Computer() {
        this.soundCard = Optional.empty();
    }

    public Computer(SoundCard soundcard) {
        this.soundCard = Optional.of(soundcard);
    }

    public SoundCard getSoundCard() {
        return soundCard.get();
    }
}

class SoundCard {
    private Optional<USB> usb;

    public SoundCard() {
        this.usb = Optional.empty();
    }

    public SoundCard(USB usb) {
        this.usb = Optional.of(usb);
    }

    public USB getUSB() {
        return usb.get();
    }
}

class USB {
    public String getVersion() {
        return "2.0";
    }
}