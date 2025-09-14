package com.dmslob.patterns.creational;

import org.testng.annotations.Test;

public class AbstractFactoryTest {
    // Abstract Product A: Button
    interface Button {
        void paint();
    }

    // Abstract Product B: Checkbox
    interface Checkbox {
        void paint();
    }

    // Concrete Product A1: Windows Button
    static class WindowsButton implements Button {
        @Override
        public void paint() {
            System.out.println("Rendering a Windows button.");
        }
    }

    // Concrete Product A2: macOS Button
    static class MacOSButton implements Button {
        @Override
        public void paint() {
            System.out.println("Rendering a MacOS button.");
        }
    }

    // Concrete Product B1: Windows Checkbox
    static class WindowsCheckbox implements Checkbox {
        @Override
        public void paint() {
            System.out.println("Rendering a Windows checkbox.");
        }
    }

    // Concrete Product B2: macOS Checkbox
    static class MacOSCheckbox implements Checkbox {
        @Override
        public void paint() {
            System.out.println("Rendering a MacOS checkbox.");
        }
    }

    // Abstract Factory: GUIFactory
    interface GUIFactory {
        Button createButton();
        Checkbox createCheckbox();
    }

    // Concrete Factory 1: Windows GUI Factory
    static class WindowsGUIFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new WindowsButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WindowsCheckbox();
        }
    }

    // Concrete Factory 2: macOS GUI Factory
    static class MacOSGUIFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new MacOSButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new MacOSCheckbox();
        }
    }

    static class Application {
        private final Button button;
        private final Checkbox checkbox;

        public Application(GUIFactory factory) {
            button = factory.createButton();
            checkbox = factory.createCheckbox();
        }

        public void paintUI() {
            button.paint();
            checkbox.paint();
        }
    }

    @Test
    public void should_test_abstract_factory() {
        GUIFactory windowsFactory = new WindowsGUIFactory();
        Application windowsApp = new Application(windowsFactory);

        System.out.println("Windows Application:");
        windowsApp.paintUI();
        System.out.println("\n---");

        GUIFactory macosFactory = new MacOSGUIFactory();
        Application macosApp = new Application(macosFactory);
        System.out.println("MacOS Application:");
        macosApp.paintUI();
    }
}
