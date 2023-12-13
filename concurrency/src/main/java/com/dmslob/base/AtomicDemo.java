package com.dmslob.base;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

	public static void main(String[] args) {
		System.out.println("start...");
		Inc inc = new Inc();
		System.out.println("after Inc inc...");
		new MyThread(inc).start();
		new MyThread(inc).start();
	}
}

class Inc {

	AtomicInteger i = new AtomicInteger(1);
	boolean cancel = false;

	synchronized void inc() {
		i.getAndAdd(2);
		if (i.get() % 2 != 0) {
			cancel = true;
		}
	}
}

class MyThread extends Thread {

	Inc inc;

	MyThread(Inc _inc) {
		inc = _inc;
	}

	@Override
	public void run() {
		System.out.println("run...");
		while (true) {
			System.out.println("true...");
			if (inc.cancel) {
				System.out.println(inc.i);
				break;
			}
			inc.inc();
		}
		System.out.println(inc.i);
	}
}
