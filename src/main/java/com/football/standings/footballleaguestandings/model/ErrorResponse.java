package com.football.standings.footballleaguestandings.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    List<Error> errors = new ArrayList<>();
}
