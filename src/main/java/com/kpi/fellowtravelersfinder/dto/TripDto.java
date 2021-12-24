package com.kpi.fellowtravelersfinder.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Data
@Getter
@Setter
public class TripDto {
    private String departurePoint;
    private String arrivalPoint;
    private Date dateDep;
    private Date dateArr;
    private String username;


}
