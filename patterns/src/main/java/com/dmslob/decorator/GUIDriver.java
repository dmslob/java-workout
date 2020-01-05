package com.dmslob.decorator;

public class GUIDriver {

    public static void main(String[] args) {
        // create a new window
        Window window = new SimpleWindow();
        window.renderWindow();

        // at some point later
        // maybe text size becomes larger than the window
        // thus the scrolling behavior must be added

        // decorate old window
        window = new ScrollableWindow(window);

        //  now window object
        // has additional behavior / state

        window.renderWindow();
    }
}

/**
 * Window Interface
 * <p>
 * Component window
 */
interface Window {
    public void renderWindow();
}

/**
 * Window implementation
 * <p>
 * Concrete implementation
 */
class SimpleWindow implements Window {

    @Override
    public void renderWindow() {
        // implementation of rendering details
        System.out.println("SimpleWindow");
    }
}

class DecoratedWindow implements Window {

    /**
     * private reference to the window being decorated
     */
    private Window privateWindowReference = null;

    public DecoratedWindow(Window windowRefernce) {

        this.privateWindowReference = windowRefernce;
    }

    @Override
    public void renderWindow() {
        System.out.println("DecoratedWindow");
        privateWindowReference.renderWindow();
    }
}

/**
 * Concrete Decorator with extended state
 * <p>
 * Scrollable window creates a window that is scrollable
 */
class ScrollableWindow extends DecoratedWindow {
    /**
     * Additional State
     */
    private Object scrollBarObjectRepresentation;

    public ScrollableWindow(Window windowRefernce) {
        super(windowRefernce);
    }

    @Override
    public void renderWindow() {
        System.out.println("ScrollableWindow");
        // render scroll bar
        renderScrollBarObject();
        // render decorated window
        super.renderWindow();
    }

    private void renderScrollBarObject() {
        // prepare scroll bar
        scrollBarObjectRepresentation = new Object();
        // render scrollbar
    }
}