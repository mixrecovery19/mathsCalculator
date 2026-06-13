package com.totalbeginner.mathsCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {

    @Test
    public void testPerson() {
        Person p1 = new Person();
        assertEquals("unknown name", p1.getName());
        assertEquals(3, p1.getMaximumBooks());
    }
    @Test
    public void testSetName() {
        Person p2 = new Person();
        p2.setName("John Doe");
        assertEquals("John Doe", p2.getName());
        if (!p2.getName().equals("John Doe")) {
            fail("This test is not yet implemented");
        } else {
            System.out.println("Testing setName() method: " + p2.getName());
        }
    }

    @Test
    public void testSetMaximumBooks() {
        Person p3 = new Person();
        p3.setMaximumBooks(7);
        assertEquals(7, p3.getMaximumBooks());
        if (p3.getMaximumBooks() != 7) {
            fail("This test is not yet implemented");
        } else {
            System.out.println("Testing setMaximumBooks() method: " + p3.getMaximumBooks());
        }
    }
}