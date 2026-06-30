package com.totalbeginner.mathsCalculator;
/*As I am learning, A Bachelor of I.T. Student, which is how this project developed, to "sink in" some of the math and programming concepts 
I learned in the first semester I decided to leave some of the code as examples. They may help you too.*/
public class Scratch {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Michael");
        p.setMaximumBooks(10);        

        System.out.println("Testing" + ' ' +p.getName() + " can borrow " + p.getMaximumBooks() + " books.");               
    }
}