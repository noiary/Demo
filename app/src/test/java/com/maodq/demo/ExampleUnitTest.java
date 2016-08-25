package com.maodq.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        int a = 2, b = 3, c = 4;
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;

        a ^= b;
        b ^= a;
        a ^= b;


//        1 = 2 ^ 3;
//        2 = 3 ^ 1;
//        3 = 1 ^ 2;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}