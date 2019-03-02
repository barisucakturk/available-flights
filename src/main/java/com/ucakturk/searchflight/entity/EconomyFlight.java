package com.ucakturk.searchflight.entity;

import lombok.Data;

@Data
public class EconomyFlight {

    private String departure;

    private String arrival;

    private long arrivalTime;

    private long departureTime;
}
