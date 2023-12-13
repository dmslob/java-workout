package com.dmslob.behavioral.visitor;

public class Circle extends Dot {
	private final int radius;

	public Circle(int id, int x, int y, int radius) {
		super(id, x, y);
		this.radius = radius;
	}

	@Override
	public String accept(Visitor visitor) {
		return visitor.visitCircle(this);
	}

	public int getRadius() {
		return radius;
	}
}
