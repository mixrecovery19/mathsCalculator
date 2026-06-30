/*As I am learning, A Bachelor of I.T. Student, which is how this project developed, to "sink in" some of the math and programming concepts 
I learned in the first semester I decided to leave some of the code as examples. They may help you too.*/
package com.totalbeginner.mathsCalculator.model;
import jakarta.persistence.*;

@Entity// JPA annotation to mark this class as a database entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;    
    private Boolean isAdmin = false;   
   
    // 🔹 Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    // 🔹 Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    // 🔹 Name
    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }   

    // establishes an admin role for the person entity allowing us to differentiate between users and admins in the application
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Boolean isAdmin() {
    return isAdmin;
    }
    // The setter for isAdmin allows us to assign admin privileges to a user
    public void setAdmin(Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    //separates the user from the admin role allowing me to create a user/admin type of flow to the project
    public Boolean isUser() {
        return !isAdmin;
    }
}