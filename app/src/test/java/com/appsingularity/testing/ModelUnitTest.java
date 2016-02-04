package com.appsingularity.testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModelUnitTest {
    private Model mModel = new Model();

    @Test
    public void testTwo() {
        assertTrue(mModel.isNumberEven(2));
    }

    @Test
    public void testOne() {
        assertFalse(mModel.isNumberEven(1));
    }

    @Test
    public void testSeven() {
        assertFalse(mModel.isNumberEven(7));
    }

}