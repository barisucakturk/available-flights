package com.ucakturk.searchflight.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BusinessFlight {

    private String flight;

    private LocalDateTime departure;

    private LocalDateTime arrival;

}
