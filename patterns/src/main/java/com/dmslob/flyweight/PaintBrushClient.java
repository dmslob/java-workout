package com.dmslob.flyweight;

public class PaintBrushClient {

    public static void main(String[] args) {
        Pen yellowThickPen1 = PenFactory.getThickPen("YELLOW");  //created new pen
        yellowThickPen1.draw("Hello World !!");

        Pen yellowThickPen2 = PenFactory.getThickPen("YELLOW");  //pen is shared
        yellowThickPen2.draw("Hello World !!");

        Pen blueThickPen = PenFactory.getThickPen("BLUE");       //created new pen
        blueThickPen.draw("Hello World !!");

        System.out.println(yellowThickPen1.hashCode());
        System.out.println(yellowThickPen2.hashCode());

        System.out.println(blueThickPen.hashCode());
    }
}
