package com.example.santa.model;

// Old import
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;

// Updated import for Spring Boot 3.x / Jakarta EE
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private Long secretSantaFor;

    // Getters and Setters (or use Lombok with @Data annotation)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSecretSantaFor() {
        return secretSantaFor;
    }

    public void setSecretSantaFor(Long secretSantaFor) {
        this.secretSantaFor = secretSantaFor;
    }
}
