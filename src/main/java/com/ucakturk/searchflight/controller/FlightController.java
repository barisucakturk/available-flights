package com.ucakturk.searchflight.controller;


import com.ucakturk.searchflight.entity.dto.BusinessFlightRequestDto;
import com.ucakturk.searchflight.entity.dto.EconomyFlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import com.ucakturk.searchflight.mapper.ResponseDtoMapper;
import com.ucakturk.searchflight.service.BusinessFlightClientService;
import com.ucakturk.searchflight.service.EconomyFlightClientService;
import com.ucakturk.searchflight.service.EconomyFlightService;
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

    private final EconomyFlightService economyFlightService;

    private final BusinessFlightClientService businessFlightClientService;


    @Autowired
    public FlightController(EconomyFlightClientService economyFlightClientService, EconomyFlightService economyFlightService, BusinessFlightClientService businessFlightClientService) {
        this.economyFlightClientService = economyFlightClientService;
        this.economyFlightService = economyFlightService;
        this.businessFlightClientService = businessFlightClientService;
    }

    @GetMapping("/cheap")
    public Flux<FlightResponseDto> getEconomyFlights(@ModelAttribute EconomyFlightRequestDto economyFlightRequestDto) {
        return economyFlightClientService.getFlights()
                .map(ResponseDtoMapper::economyFlightToResponseDto)
                .filter(economyFlightService.createEconomyPredicates(economyFlightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime));

    }

    @GetMapping("/business")
    public Flux<FlightResponseDto> getBusinessFlights(@ModelAttribute BusinessFlightRequestDto businessFlightRequestDto) {
        return businessFlightClientService.getFlights()
                .map(ResponseDtoMapper::businessFlightToResponseDto)
                .filter(economyFlightService.createBusinessPredicates(businessFlightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime));

    }

}
