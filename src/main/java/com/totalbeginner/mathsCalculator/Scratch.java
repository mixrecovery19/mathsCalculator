package com.totalbeginner.mathsCalculator;

public class Scratch {

    public static void main(
            String[] args
    ) {

        Person p = new Person();
        p.setName("Michael");
        p.setMaximumBooks(10);        

        System.out.println(
                "Testing" + ' ' +p.getName() + " can borrow " + p.getMaximumBooks() + " books."
        );
        
    }
}