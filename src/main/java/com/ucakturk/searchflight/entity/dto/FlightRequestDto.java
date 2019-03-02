package com.ucakturk.searchflight.entity.dto;

import lombok.Data;

@Data
public class FlightRequestDto {

    private String arrival;

    private String departure;

    private String arrivalDate;

    private String departureDate;

    private int page;

    private int count;
}
