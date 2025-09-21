package com.dmslob.patterns.behavioral;


import lombok.Setter;
import org.testng.annotations.Test;

public class CommandTest {

    @Test
    public void should_test_command() {
        // given
        // Create the receiver
        var light = new Light();
        // Create the concrete commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        // Create the invoker
        RemoteControl remote = new RemoteControl();
        // when
        remote.setCommand(lightOn);
        remote.pressButton();
        // Turn the light off
        remote.setCommand(lightOff);
        remote.pressButton();
    }

    /**
     * The Light class is the receiver.
     * It performs the actual work, such as turning on or off.
     */
    static class Light {
        public void turnOn() {
            System.out.println("The light is on.");
        }

        public void turnOff() {
            System.out.println("The light is off.");
        }
    }
    // Command interface
    interface Command {
        void execute();
    }

    /**
     * The LightOnCommand and LightOffCommand are concrete commands
     * that encapsulate a request to the Light receiver.
     */
    static class LightOnCommand implements Command {
        private final Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    static class LightOffCommand implements Command {
        private final Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    /**
     * It is the invoker.
     * It holds a command object and executes the command when instructed,
     * without knowing the details of the concrete command or the receiver.
     */
    @Setter
    static class RemoteControl {
        private Command command;

        public void pressButton() {
            command.execute();
        }
    }
}
