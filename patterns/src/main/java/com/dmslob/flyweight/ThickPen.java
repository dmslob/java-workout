package com.dmslob.flyweight;

public class ThickPen implements Pen {
    //intrinsic state - shareable
    final BrushSize brushSize = BrushSize.THICK;

    //extrinsic state - supplied by client
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(String content) {
        System.out.println("Drawing THICK content in color: " + color);
    }
}
