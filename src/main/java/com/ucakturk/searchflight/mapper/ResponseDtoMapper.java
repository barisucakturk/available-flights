package com.ucakturk.searchflight.mapper;

import com.ucakturk.searchflight.entity.BusinessFlight;
import com.ucakturk.searchflight.entity.EconomyFlight;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ResponseDtoMapper {

    private ResponseDtoMapper() {

    }

    public static FlightResponseDto economyFlightToResponseDto(EconomyFlight economyFlight) {
        return FlightResponseDto.builder()
                .arrival(economyFlight.getArrival())
                .departure(economyFlight.getDeparture())
                .arrivalTime(convertEpochToLocalDateTime(economyFlight.getArrivalTime()))
                .departureTime(convertEpochToLocalDateTime(economyFlight.getDepartureTime()))
                .build();
    }

    public static FlightResponseDto businessFlightToResponseDto(BusinessFlight businessFlight) {
        return FlightResponseDto.builder()
                .departure(businessFlight.getFlight().split("->")[0])
                .arrival(businessFlight.getFlight().split("->")[1])
                .arrivalTime(businessFlight.getArrival())
                .departureTime(businessFlight.getDeparture())
                .build();
    }

    private static LocalDateTime convertEpochToLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }
}
