package com.football.standings.footballleaguestandings.controller;

import com.football.standings.footballleaguestandings.service.GetLeagueStandingsService;
import com.football.standings.footballleaguestandings.web.GetLeagueStandingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetLeagueStandingsController {

    @Autowired
    private GetLeagueStandingsService getLeagueStandingsService;


    @GetMapping
    public ResponseEntity<GetLeagueStandingsResponse> getLeagueStanding(@RequestParam String countryName,
                                                                        @RequestParam String leagueName,
                                                                        @RequestParam String teamName) {

        GetLeagueStandingsResponse leagueStanding = getLeagueStandingsService.getLeagueStanding(countryName, leagueName, teamName);
        return new ResponseEntity<>(leagueStanding, HttpStatus.OK);
    }
}
