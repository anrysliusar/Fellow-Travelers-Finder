package com.kpi.fellowtravelersfinder.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@Setter
public class TripForm {
    private String departurePoint;
    private String arrivalPoint;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateDep;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateArr;
    private String username;


}
