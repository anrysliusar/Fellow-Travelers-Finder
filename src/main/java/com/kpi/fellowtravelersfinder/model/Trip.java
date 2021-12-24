package com.kpi.fellowtravelersfinder.model;


import java.sql.Date;

public class Trip {
    private int id;
    private Date DepartureDate;
    private Date ArrivalDate;
    private User initiator;
    private Route route;

    public Trip() {
    }

    public Trip(Date departureDate, Date arrivalDate, User initiator, Route route) {
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
        this.initiator = initiator;
        this.route = route;
    }

    public Trip(int id, Date departureDate, Date arrivalDate, User initiator, Route route) {
        this.id = id;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
        this.initiator = initiator;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(Date departureDate) {
        DepartureDate = departureDate;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
