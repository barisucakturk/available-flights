package com.ucakturk.searchflight.entity.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class FlightResponseDto {

    private String arrival;

    private String departure;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;
}
