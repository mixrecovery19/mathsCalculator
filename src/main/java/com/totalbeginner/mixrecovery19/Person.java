package com.totalbeginner.mixrecovery19;

public class Person {
    //fields
    private String name; // name of the person
    private int maximumBooks; // maximum number of books the person can borrow

    //constructors
    public Person() {
        name = "unknown name";
        maximumBooks = 3;
    }
    public String getName() {
            return name;
    }

    public void setName(String anyName) {
        this.name = anyName;
    }

    public int getMaximumBooks() {
        return maximumBooks;
    }

    public void setMaximumBooks(int maximumBooks) {
        this.maximumBooks = maximumBooks;
    }
    
}
