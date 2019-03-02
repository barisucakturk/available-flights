package com.ucakturk.searchflight.service;

import com.ucakturk.searchflight.entity.Page;
import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import com.ucakturk.searchflight.mapper.ResponseDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FlightProcessService {

    private final EconomyFlightClientService economyFlightClientService;

    private final FlightService flightService;

    private final BusinessFlightClientService businessFlightClientService;


    @Autowired
    public FlightProcessService(EconomyFlightClientService economyFlightClientService, FlightService flightService, BusinessFlightClientService businessFlightClientService) {
        this.economyFlightClientService = economyFlightClientService;
        this.flightService = flightService;
        this.businessFlightClientService = businessFlightClientService;
    }

    public Mono<Page<FlightResponseDto>> getAvailableEconomyFlights(FlightRequestDto flightRequestDto) {
        return economyFlightClientService.getFlights()
                .map(ResponseDtoMapper::economyFlightToResponseDto)
                .filter(flightService.createPredicate(flightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime))
                .collectList()
                .map(flightResponseDtoList -> new Page<>(flightResponseDtoList.size(), flightRequestDto.getPage(), flightResponseDtoList.stream()
                        .skip(flightRequestDto.getCount() * flightRequestDto.getPage())
                        .limit(flightRequestDto.getCount())
                        .collect(Collectors.toList())));
    }

    public Mono<Page<FlightResponseDto>> getAvaliableBusinessFlights(FlightRequestDto flightRequestDto) {
        return businessFlightClientService.getFlights()
                .map(ResponseDtoMapper::businessFlightToResponseDto)
                .filter(flightService.createPredicate(flightRequestDto))
                .sort(Comparator.comparing(FlightResponseDto::getArrivalTime))
                .collectList()
                .map(flightResponseDtoList -> new Page<>(flightResponseDtoList.size(), flightRequestDto.getPage(), flightResponseDtoList.stream()
                        .skip(flightRequestDto.getCount() * flightRequestDto.getPage())
                        .limit(flightRequestDto.getCount())
                        .collect(Collectors.toList())));

    }
}
