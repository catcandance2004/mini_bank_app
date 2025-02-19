package com.example.bankingapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // use to auto gen setter, getter, toString, euqals ,...
@Entity // mark class Account as JPA entity
@Table(name = "accounts") // specify db table name
public class Account {
    @Id // mark id field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // configure auto increment for id field
    private Long id;
    private String name;
    private String email;
    private double balance;
}
