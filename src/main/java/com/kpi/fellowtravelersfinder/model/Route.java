package com.kpi.fellowtravelersfinder.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departurePoint;
    private String arrivalPoint;

    @OneToOne(mappedBy = "route")
    private Trip trip;
}
