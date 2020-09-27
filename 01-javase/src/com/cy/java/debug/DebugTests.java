package com.cy.java.debug;

public class DebugTests {
    static void doMethod01(){
        System.out.println("doMethod01");
        doMethod02();
    }

    private static void doMethod02() {
        System.out.println("doMethod01");
        Integer n1=100;//Integer.valueOf(100)
        Integer n2=200;
        System.out.println(n1==n2);
    }

    public static void main(String[] args) {
        System.out.println("main");
        doMethod01();
    }
}