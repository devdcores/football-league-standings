package com.football.standings.footballleaguestandings.controller;

import com.football.standings.footballleaguestandings.exception.TeamNotFoundException;
import com.football.standings.footballleaguestandings.service.GetLeagueStandingsService;
import com.football.standings.footballleaguestandings.web.GetLeagueStandingsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetLeagueStandingsControllerTest {

    @Mock
    private GetLeagueStandingsService getLeagueStandingsService;

    @InjectMocks
    private GetLeagueStandingsController getLeagueStandingsController;

    @DisplayName("Get League Standings")
    @Test
    void testGetLeagueStandings() {

        String countryName = "England";
        String leagueName = "Championship";
        String teamName = "Manchester City";

        GetLeagueStandingsResponse mockResponse = GetLeagueStandingsResponse.builder()
                .country_id(1)
                .country_name("England")
                .team_name("Manchester City")
                .league_name("Championship")
                .build();

        Mockito.when(getLeagueStandingsService.getLeagueStanding(countryName, leagueName, teamName)).thenReturn(mockResponse);
        ResponseEntity<GetLeagueStandingsResponse> leagueStanding = getLeagueStandingsController.getLeagueStanding(countryName, leagueName, teamName);

        GetLeagueStandingsResponse body = leagueStanding.getBody();
        int statusCode = leagueStanding.getStatusCodeValue();

        assertEquals("England", body.getCountry_name());
        assertEquals("Championship", body.getLeague_name());
        assertEquals("Manchester City", body.getTeam_name());
        assertEquals(200, statusCode);
    }

    @DisplayName("Get League Standings Wrong Team Name")
    @Test
    void testGetLeagueStandingsError() {

        String countryName = "England";
        String leagueName = "Championship";
        String teamName = "Manchester City London";

        GetLeagueStandingsResponse mockResponse = GetLeagueStandingsResponse.builder()
                .country_id(1)
                .country_name("England")
                .team_name("Manchester City")
                .league_name("Championship")
                .build();

        Mockito.when(getLeagueStandingsService.getLeagueStanding(countryName, leagueName, teamName)).thenThrow(new TeamNotFoundException("Team Not Found"));

        Assertions.assertThrows(TeamNotFoundException.class, () -> {
            getLeagueStandingsController.getLeagueStanding(countryName, leagueName, teamName);
        });
    }
}
