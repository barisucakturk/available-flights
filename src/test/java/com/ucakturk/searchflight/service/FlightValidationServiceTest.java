package com.ucakturk.searchflight.service;

import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.exception.FlightParametersValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class FlightValidationServiceTest {

    private FlightValidationService flightValidationService;

    @Mock
    private FlightRequestDto flightRequestDto;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        flightValidationService = new FlightValidationService();
    }

    @Test
    public void validateFlightParameters_ShouldDoNothing_WhenPageAndCountGreaterThan0() throws FlightParametersValidationException {
        //given
        //when
        when(flightRequestDto.getCount()).thenReturn(1);
        when(flightRequestDto.getPage()).thenReturn(1);
        //then
        flightValidationService.validateFlightParameters(flightRequestDto);

        verify(flightRequestDto, times(1)).getCount();
        verify(flightRequestDto, times(1)).getPage();

        verifyNoMoreInteractions(flightRequestDto);
    }

    @Test
    public void validateFlightParameters_ShouldThrowException_WhenPageOrCountEqualsTo0() {
        //given
        //when
        when(flightRequestDto.getCount()).thenReturn(0);
        when(flightRequestDto.getPage()).thenReturn(1);
        //then
        try {
            flightValidationService.validateFlightParameters(flightRequestDto);
            fail();
        } catch (FlightParametersValidationException e) {
            verify(flightRequestDto, times(1)).getCount();
            verify(flightRequestDto, times(1)).getPage();

            verifyNoMoreInteractions(flightRequestDto);

        }

    }
}