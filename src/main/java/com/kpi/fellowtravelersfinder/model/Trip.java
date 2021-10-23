package com.kpi.fellowtravelersfinder.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date DepartureDate;
    private Date ArrivalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "initiator_id", nullable = false)
    private User initiator;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;


}
