
/*As I am learning, A Bachelor of I.T. Student, which is how this project developed, to "sink in" some of the math and programming concepts 
I learned in the first semester I decided to leave some of the code as examples. They may help you too.*/
package com.totalbeginner.mathsCalculator;

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
