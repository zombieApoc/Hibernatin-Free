package com.theironyard.clt;

import javax.persistence.*;

/**
 * Created by Ultramar on 5/3/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String userName;

    @Column(nullable = false)
    String passwordHash;

    public User() {
    }

    public User(String userName, String passwordHash) {
        this.userName = userName;
        this.passwordHash = passwordHash;
    }
}
