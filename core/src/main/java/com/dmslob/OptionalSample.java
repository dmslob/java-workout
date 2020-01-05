package com.dmslob;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");
        Optional<String> name1 = Optional.empty();
        name.isPresent();
        System.out.println(name1.get());

        USB usb = new USB();
        Soundcard soundcard = new Soundcard(usb);
        Computer computer = new Computer(soundcard);
        System.out.println(computer.getSoundcard().getUSB().getVersion());
    }
}

class Computer {
    private Optional<Soundcard> soundcard;

    public Computer() {
        this.soundcard = Optional.empty();
    }

    public Computer(Soundcard soundcard) {
        this.soundcard = Optional.of(soundcard);
    }

    public Soundcard getSoundcard() {
        return soundcard.get();
    }
}

class Soundcard {
    private Optional<USB> usb;

    public Soundcard() {
        this.usb = Optional.empty();
    }

    public Soundcard(USB usb) {
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