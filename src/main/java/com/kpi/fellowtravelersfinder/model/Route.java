package com.kpi.fellowtravelersfinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departurePoint;
    private String arrivalPoint;

    @OneToMany(mappedBy = "route", cascade = {CascadeType.ALL})
    private List<Trip> trips;
}
