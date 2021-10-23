package com.kpi.fellowtravelersfinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "route")
    private Trip trip;


    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                '}';
    }
}
