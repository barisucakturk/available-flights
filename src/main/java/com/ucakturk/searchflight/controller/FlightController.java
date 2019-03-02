package com.ucakturk.searchflight.controller;


import com.ucakturk.searchflight.entity.Page;
import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import com.ucakturk.searchflight.service.FlightProcessService;
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


    @Autowired
    public FlightController(FlightProcessService flightProcessService) {
        this.flightProcessService = flightProcessService;
    }

    @GetMapping("/cheap")
    public Mono<Page<FlightResponseDto>> getEconomyFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        //ServerResponse.badRequest
        return flightProcessService.getAvailableEconomyFlights(flightRequestDto);
    }

    @GetMapping("/business")
    public Mono<Page<FlightResponseDto>> getBusinessFlights(@ModelAttribute FlightRequestDto flightRequestDto) {
        return flightProcessService.getAvaliableBusinessFlights(flightRequestDto);

    }

}
