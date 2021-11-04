package com.kpi.fellowtravelersfinder.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    private String email;
    private String phone;

    @OneToMany(mappedBy = "initiator", cascade = {CascadeType.ALL})
    private List<Trip> trips;

}
