package com.ucakturk.searchflight.controller;


import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import com.ucakturk.searchflight.mapper.ResponseDtoMapper;
import com.ucakturk.searchflight.service.BusinessFlightClientService;
import com.ucakturk.searchflight.service.EconomyFlightClientService;
import com.ucakturk.searchflight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@RestController
@RequestMapping("search-flights")
public class FlightController {

    private final EconomyFlightClientService economyFlightClientService;

    private final FlightService flightService;

    private final BusinessFlightClientService businessFlightClientService;


    @Autowired
    public FlightController(EconomyFlightClientService economyFlightClientService, FlightService flightService, BusinessFlightClientService businessFlightClientService) {
        this.economyFlightClientService = economyFlightClientService;
        this.flightService = flightService;
        this.businessFlightClientService = businessFlightClientService;
    }

    @GetMapping("/cheap")
    public Flux<FlightResponseDto> getEconomyFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        return economyFlightClientService.getFlights()
                .map(ResponseDtoMapper::economyFlightToResponseDto)
                .filter(flightService.createPredicate(flightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime));

    }

    @GetMapping("/business")
    public Flux<FlightResponseDto> getBusinessFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        return businessFlightClientService.getFlights()
                .map(ResponseDtoMapper::businessFlightToResponseDto)
                .filter(flightService.createPredicate(flightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime));

    }

}
