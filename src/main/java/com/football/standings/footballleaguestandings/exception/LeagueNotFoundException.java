package com.football.standings.footballleaguestandings.exception;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(String message) {
        super(message);
    }
}