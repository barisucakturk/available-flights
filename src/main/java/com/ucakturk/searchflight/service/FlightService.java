package com.ucakturk.searchflight.service;


import com.ucakturk.searchflight.entity.dto.BusinessFlightRequestDto;
import com.ucakturk.searchflight.entity.dto.EconomyFlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

@Service
public class FlightService {


    public Predicate<FlightResponseDto> createEconomyPredicates(EconomyFlightRequestDto economyFlightRequestDto) {

        Predicate<FlightResponseDto> economyFlightPredicate = flightResponseDto -> true;
        if (!StringUtils.isEmpty(economyFlightRequestDto.getArrival())) {
            Predicate<FlightResponseDto> flightPredicateArrival = flightResponseDto -> flightResponseDto.getArrival().equals(economyFlightRequestDto.getArrival());
            economyFlightPredicate = economyFlightPredicate.and(flightPredicateArrival);
        }
        if (!StringUtils.isEmpty(economyFlightRequestDto.getDeparture())) {
            Predicate<FlightResponseDto> flightPredicateDeparture = flightResponseDto -> flightResponseDto.getDeparture().equals(economyFlightRequestDto.getDeparture());
            economyFlightPredicate = economyFlightPredicate.and(flightPredicateDeparture);
        }

        return economyFlightPredicate;
    }

    public Predicate<FlightResponseDto> createBusinessPredicates(BusinessFlightRequestDto businessFlightRequestDto) {

        Predicate<FlightResponseDto> businessFlightPredicate = businessFlight -> true;
        if (!StringUtils.isEmpty(businessFlightRequestDto.getArrival())) {
            Predicate<FlightResponseDto> flightPredicateArrival = businessFlight -> businessFlight.getArrival().equals(businessFlightRequestDto.getArrival());
            businessFlightPredicate = businessFlightPredicate.and(flightPredicateArrival);
        }
        if (!StringUtils.isEmpty(businessFlightRequestDto.getDeparture())) {
            Predicate<FlightResponseDto> flightPredicateDeparture = businessFlight -> businessFlight.getDeparture().equals(businessFlightRequestDto.getDeparture());
            businessFlightPredicate = businessFlightPredicate.and(flightPredicateDeparture);
        }

        return businessFlightPredicate;
    }
}
