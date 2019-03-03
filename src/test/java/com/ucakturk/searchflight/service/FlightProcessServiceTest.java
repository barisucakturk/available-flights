package com.ucakturk.searchflight.service;

import com.ucakturk.searchflight.entity.BusinessFlight;
import com.ucakturk.searchflight.entity.EconomyFlight;
import com.ucakturk.searchflight.entity.Page;
import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class FlightProcessServiceTest {

    private FlightProcessService flightProcessService;

    @Mock
    private EconomyFlightClientService economyFlightClientService;
    @Mock
    private FlightService flightService;
    @Mock
    private BusinessFlightClientService businessFlightClientService;

    private Flux<EconomyFlight> economyFlightFlux;

    private Flux<BusinessFlight> businessFlightFlux;

    private EconomyFlight economyFlight;

    private BusinessFlight businessFlight;

    @Mock
    private FlightRequestDto flightRequestDto;

    private long arrivalTime;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        flightProcessService = new FlightProcessService(economyFlightClientService, flightService, businessFlightClientService);
        economyFlight = new EconomyFlight();
        economyFlight.setArrival("JFK");
        economyFlight.setDeparture("Istanbul");
        arrivalTime = 1012472161;
        economyFlight.setArrivalTime(arrivalTime);
        economyFlightFlux = Flux.just(economyFlight);

        businessFlight = new BusinessFlight();
        businessFlight.setFlight("Singapore  ->  Toronto");
        businessFlight.setArrival(LocalDateTime.now());
        businessFlightFlux = Flux.just(businessFlight);
    }

    @Test
    public void getAvailableEconomyFlights_ShouldReturnAvailableFlights_WhenParamsValid() {
        //given
        Predicate<FlightResponseDto> flightResponseDtoPredicate = flightResponseDto -> true;
        //when
        when(economyFlightClientService.getFlights()).thenReturn(economyFlightFlux);
        when(flightService.createPredicate(flightRequestDto)).thenReturn(flightResponseDtoPredicate);
        when(flightRequestDto.getPage()).thenReturn(1);
        when(flightRequestDto.getCount()).thenReturn(1);
        //then
        Mono<Page<FlightResponseDto>> result = flightProcessService.getAvailableEconomyFlights(flightRequestDto);

        assertNotNull(result);
        assertEquals(1, result.block().getPageNumber());

        verify(economyFlightClientService, times(1)).getFlights();
        verify(flightService, times(1)).createPredicate(flightRequestDto);
        verifyNoMoreInteractions(economyFlightClientService, flightService);
    }

    @Test
    public void getAvaliableBusinessFlights_ShouldReturnAvailableFlights_WhenParamsValid() {

        //given
        Predicate<FlightResponseDto> flightResponseDtoPredicate = flightResponseDto -> true;
        //when
        when(businessFlightClientService.getFlights()).thenReturn(businessFlightFlux);
        when(flightService.createPredicate(flightRequestDto)).thenReturn(flightResponseDtoPredicate);
        when(flightRequestDto.getPage()).thenReturn(1);
        when(flightRequestDto.getCount()).thenReturn(1);
        //then
        Mono<Page<FlightResponseDto>> result = flightProcessService.getAvaliableBusinessFlights(flightRequestDto);

        assertNotNull(result);
        assertEquals(1, result.block().getPageNumber());

        verify(businessFlightClientService, times(1)).getFlights();
        verify(flightService, times(1)).createPredicate(flightRequestDto);
        verifyNoMoreInteractions(businessFlightClientService, flightService);
    }
}