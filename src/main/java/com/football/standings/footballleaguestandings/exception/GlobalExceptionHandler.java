package com.football.standings.footballleaguestandings.exception;

import com.football.standings.footballleaguestandings.model.Error;
import com.football.standings.footballleaguestandings.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(CountryNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setField("countryName");
        error.setMessage(ex.getMessage());

        errorResponse.getErrors().add(error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LeagueNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(LeagueNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setField("leagueName");
        error.setMessage(ex.getMessage());

        errorResponse.getErrors().add(error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(TeamNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setField("teamName");
        error.setMessage(ex.getMessage());

        errorResponse.getErrors().add(error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setField("footballApi");
        error.setMessage(ex.getMessage());

        errorResponse.getErrors().add(error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
