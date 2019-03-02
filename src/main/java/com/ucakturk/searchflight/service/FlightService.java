package com.ucakturk.searchflight.service;


import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

@Service
public class FlightService {


    public Predicate<FlightResponseDto> createPredicate(FlightRequestDto flightRequestDto) {

        Predicate<FlightResponseDto> economyFlightPredicate = flightResponseDto -> true;
        if (!StringUtils.isEmpty(flightRequestDto.getArrival())) {
            Predicate<FlightResponseDto> flightPredicateArrival = flightResponseDto -> flightResponseDto.getArrival().equals(flightRequestDto.getArrival());
            economyFlightPredicate = economyFlightPredicate.and(flightPredicateArrival);
        }
        if (!StringUtils.isEmpty(flightRequestDto.getDeparture())) {
            Predicate<FlightResponseDto> flightPredicateDeparture = flightResponseDto -> flightResponseDto.getDeparture().equals(flightRequestDto.getDeparture());
            economyFlightPredicate = economyFlightPredicate.and(flightPredicateDeparture);
        }


        return economyFlightPredicate;
    }
}
