package com.dmslob.patterns.behavioral;

import org.testng.annotations.Test;

public class StateTest {
    /**
     * enables an object to modify its behavior when its internal state changes.
     */
    // State Interface
    interface TVState {
        void pressPowerButton(TV tv);
        void pressChannelUp(TV tv);
    }

    // Concrete State: TVOnState
    class TVOnState implements TVState {
        @Override
        public void pressPowerButton(TV tv) {
            System.out.println("Turning TV Off.");
            tv.setState(new TVOffState());
        }

        @Override
        public void pressChannelUp(TV tv) {
            System.out.println("Changing channel up.");
        }
    }

    // Concrete State: TVOffState
    class TVOffState implements TVState {
        @Override
        public void pressPowerButton(TV tv) {
            System.out.println("Turning TV On.");
            tv.setState(new TVOnState());
        }

        @Override
        public void pressChannelUp(TV tv) {
            System.out.println("TV is off, cannot change channel.");
        }
    }

    // Context: TV
    class TV {
        private TVState currentState;

        public TV() {
            this.currentState = new TVOffState();
        }

        public void setState(TVState state) {
            this.currentState = state;
        }

        public void pressPowerButton() {
            currentState.pressPowerButton(this);
        }

        public void pressChannelUp() {
            currentState.pressChannelUp(this);
        }
    }

    @Test
    public void should_test_state_pattern() {
        // given
        TV tv = new TV();
        // when
        tv.pressChannelUp(); // TV is off, cannot change channel.
        tv.pressPowerButton(); // Turning TV On.
        tv.pressChannelUp(); // Changing channel up.
        tv.pressPowerButton(); // Turning TV Off.
    }
}
