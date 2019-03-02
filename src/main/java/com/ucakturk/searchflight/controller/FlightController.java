package com.ucakturk.searchflight.controller;


import com.ucakturk.searchflight.entity.Page;
import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import com.ucakturk.searchflight.exception.FlightParametersValidationException;
import com.ucakturk.searchflight.service.FlightProcessService;
import com.ucakturk.searchflight.service.FlightValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("search-flights")
public class FlightController {

    private final FlightProcessService flightProcessService;

    private final FlightValidationService flightValidationService;

    @Autowired
    public FlightController(FlightProcessService flightProcessService, FlightValidationService flightValidationService) {
        this.flightProcessService = flightProcessService;
        this.flightValidationService = flightValidationService;
    }

    @GetMapping("/cheap")
    public Mono<Page<FlightResponseDto>> getEconomyFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        try {
            flightValidationService.validateFlightParameters(flightRequestDto);
            return flightProcessService.getAvailableEconomyFlights(flightRequestDto);
        } catch (FlightParametersValidationException e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/business")
    public Mono<Page<FlightResponseDto>> getBusinessFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        try {
            flightValidationService.validateFlightParameters(flightRequestDto);
            return flightProcessService.getAvaliableBusinessFlights(flightRequestDto);
        } catch (FlightParametersValidationException e) {
            return Mono.error(e);
        }
    }

}
