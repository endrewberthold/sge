package org.sge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    @Column(unique = true)
    private String document;

    private String address;

    private String phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private List<Vehicle> vehicles;

}
