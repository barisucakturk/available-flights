package com.ucakturk.searchflight.service;

import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.entity.dto.FlightResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class FlightServiceTest {

    private FlightService flightService;

    @Mock
    private FlightRequestDto flightRequestDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        flightService = new FlightService();
    }

    @Test
    public void createPredicate() {
        //given
        String arrival = "Hong-Kong";
        String departure = "Beijing";
        String departureTime = LocalDateTime.now().toString();
        String arrivalTime = LocalDateTime.of(2019, 3, 8, 12, 11).toString();
        //when
        when(flightRequestDto.getArrival()).thenReturn(arrival);
        when(flightRequestDto.getDeparture()).thenReturn(departure);
        when(flightRequestDto.getArrivalDate()).thenReturn(arrivalTime);
        when(flightRequestDto.getDepartureDate()).thenReturn(departureTime);
        //then
        Predicate<FlightResponseDto> flightResponseDtoPredicate = flightService.createPredicate(flightRequestDto);

        assertNotNull(flightResponseDtoPredicate);


        verify(flightRequestDto, times(1)).getArrival();
        verify(flightRequestDto, times(1)).getDeparture();
        verify(flightRequestDto, times(1)).getArrivalDate();
        verify(flightRequestDto, times(1)).getDepartureDate();

        verifyNoMoreInteractions(flightRequestDto);
    }
}