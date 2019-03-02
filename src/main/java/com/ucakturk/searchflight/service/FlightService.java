package com.ucakturk.searchflight.service;


import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

@Service
public class FlightService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Predicate<FlightResponseDto> createPredicate(FlightRequestDto flightRequestDto) {

        Predicate<FlightResponseDto> flightPredicate = flightResponseDto -> true;
        if (!StringUtils.isEmpty(flightRequestDto.getArrival())) {
            Predicate<FlightResponseDto> flightPredicateArrival = flightResponseDto -> flightResponseDto.getArrival().equals(flightRequestDto.getArrival());
            flightPredicate = flightPredicate.and(flightPredicateArrival);
        }
        if (!StringUtils.isEmpty(flightRequestDto.getDeparture())) {
            Predicate<FlightResponseDto> flightPredicateDeparture = flightResponseDto -> flightResponseDto.getDeparture().equals(flightRequestDto.getDeparture());
            flightPredicate = flightPredicate.and(flightPredicateDeparture);
        }

        if (!StringUtils.isEmpty(flightRequestDto.getArrivalDate())) {
            Predicate<FlightResponseDto> flightPredicateArrivalDate = flightResponseDto -> localDateTimeToEpoch(flightResponseDto.getArrivalTime()) >
                    localDateTimeToEpoch(strToLocalDateTime(flightRequestDto.getArrivalDate()));
            flightPredicate = flightPredicate.and(flightPredicateArrivalDate);
        }

        if (!StringUtils.isEmpty(flightRequestDto.getDepartureDate())) {
            Predicate<FlightResponseDto> flightPredicateDepartureDate = flightResponseDto -> localDateTimeToEpoch(flightResponseDto.getDepartureTime()) <
                    localDateTimeToEpoch(strToLocalDateTime(flightRequestDto.getDepartureDate()));
            flightPredicate = flightPredicate.and(flightPredicateDepartureDate);
        }
        return flightPredicate;

    }

    private long localDateTimeToEpoch(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private LocalDateTime strToLocalDateTime(String date) {
        return LocalDateTime.parse(date, formatter);
    }
}
