package com.ucakturk.searchflight.exception;

import org.slf4j.helpers.MessageFormatter;

public class FlightParametersValidationException extends Exception {

    public FlightParametersValidationException(String message, Object... params) {
        super(MessageFormatter.arrayFormat(message, params).getMessage());
    }
}
