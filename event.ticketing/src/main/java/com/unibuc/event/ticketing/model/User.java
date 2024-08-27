package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String name;
    @OneToOne
    private User user;
}
