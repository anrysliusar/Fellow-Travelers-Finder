package com.kpi.fellowtravelersfinder.model;

public class Route {
    private int id;
    private String departurePoint;
    private String arrivalPoint;

    public Route() {
    }

    public Route(String arrivalPoint, String departurePoint) {
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
    }

    public Route(int id, String arrivalPoint, String departurePoint) {
        this.id = id;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }
}
