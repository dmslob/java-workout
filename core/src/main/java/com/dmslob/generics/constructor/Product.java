package com.dmslob.generics.constructor;

import java.io.Serializable;

public class Product implements Rankable, Serializable {
	private final String name;
	private final double price;
	private final int sale;

	public Product(String name, double price, int sale) {
		this.name = name;
		this.price = price;
		this.sale = sale;
	}

	@Override
	public int getRank() {
		return sale;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				", sale=" + sale +
				'}';
	}
}
