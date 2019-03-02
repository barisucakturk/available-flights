package com.ucakturk.searchflight.service;

import com.ucakturk.searchflight.entity.dto.FlightRequestDto;
import com.ucakturk.searchflight.exception.FlightParametersValidationException;
import org.springframework.stereotype.Service;

@Service
public class FlightValidationService {

    public void validateFlightParameters(FlightRequestDto flightRequestDto) throws FlightParametersValidationException {
        if (flightRequestDto.getPage() == 0 || flightRequestDto.getCount() == 0) {
            throw new FlightParametersValidationException("Page Number or Count should be greater than 0!");
        }
    }
}
