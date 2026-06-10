package org.sge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mark;

    private String model;

    private String color;

    private String plate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
