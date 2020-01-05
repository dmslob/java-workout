package com.dmslob.lambdas;

public class CompilerDemo {

    private String name;
    private String inc;

    public static void main(String[] args) throws InterruptedException {
        CompilerDemo compilerDemo = new CompilerDemo();
        compilerDemo.testLambda();
    }

    public void testLambda() {
        Thread thread = new Thread(() -> {
            String res = this.name + " inc.";
            this.inc = res;
        });
        thread.start();
    }
}
