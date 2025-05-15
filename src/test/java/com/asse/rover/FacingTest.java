package com.asse.rover;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FacingTest {
    @Test
    public void testFromValue() {
        assertEquals(Facing.fromValue(0), Facing.N);
        assertEquals(Facing.fromValue(1), Facing.E);
        assertEquals(Facing.fromValue(2), Facing.S);
        assertEquals(Facing.fromValue(3), Facing.W);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFromValueInvalid() {
        Facing.fromValue(4);
    }

    @Test
    public void testFromString() {
        assertEquals(Facing.fromString("N"), Facing.N);
        assertEquals(Facing.fromString("E"), Facing.E);
        assertEquals(Facing.fromString("S"), Facing.S);
        assertEquals(Facing.fromString("W"), Facing.W);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFromStringInvalid() {
        Facing.fromString("X");
    }

}