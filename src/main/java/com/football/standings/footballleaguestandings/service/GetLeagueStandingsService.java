package com.football.standings.footballleaguestandings.service;

import com.football.standings.footballleaguestandings.cache.FootBallApiResultCache;
import com.football.standings.footballleaguestandings.exception.CountryNotFoundException;
import com.football.standings.footballleaguestandings.exception.LeagueNotFoundException;
import com.football.standings.footballleaguestandings.exception.TeamNotFoundException;
import com.football.standings.footballleaguestandings.feign.FootballApiFeignClient;
import com.football.standings.footballleaguestandings.model.Standings;
import com.football.standings.footballleaguestandings.service.validate.ValidationService;
import com.football.standings.footballleaguestandings.web.GetLeagueStandingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GetLeagueStandingsService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private FootballApiFeignClient footballApiFeignClient;

    @Autowired
    private FootBallApiResultCache footBallApiResultCache;

    @Value("${api.football.apiKey}")
    private String apiKey;

    public GetLeagueStandingsResponse getLeagueStanding(String countryName, String leagueName, String teamName) {

        validationService.validateGetLeagueStandingRequest(countryName, leagueName, teamName);

        Map<String, Integer> countriesMap = footBallApiResultCache.getCountries(apiKey);
        if (!countriesMap.containsKey(countryName)) {
            throw new CountryNotFoundException("Country: " + countryName + ", may not be valid or the country may not have any Leagues");
        }

        int countryId = countriesMap.get(countryName);

        Map<String, Integer> leagueMap = footBallApiResultCache.getLeagues(countryId, apiKey);
        if (!leagueMap.containsKey(leagueName)) {
            throw new LeagueNotFoundException("League: " + leagueName + ", may not be valid or the league may not belong to Country: " + countryName);
        }

        int leagueId = leagueMap.get(leagueName);

        Map<String, Integer> teamsMap = footBallApiResultCache.getTeams(leagueId, apiKey);

        if (!teamsMap.containsKey(teamName)) {
            throw new TeamNotFoundException("Team: " + teamName + ", may not be valid or the team may not belong to League: " + leagueName);
        }

        int teamId = teamsMap.get(teamName);

        List<Standings> standings = footballApiFeignClient.getStandings(leagueId, apiKey);

        Optional<Standings> teamPosition = standings.stream().filter(standing -> standing.getTeam_id()==teamId).findFirst();

        if (teamPosition.isPresent()) {
            Standings standingsResult = teamPosition.get();
            GetLeagueStandingsResponse getLeagueStandingsResponse = GetLeagueStandingsResponse.builder()
                    .league_id(leagueId)
                    .league_name(leagueName)
                    .country_id(countryId)
                    .country_name(countryName)
                    .team_key(standingsResult.getTeam_id())
                    .team_name(teamName)
                    .position(standingsResult.getOverall_league_position())
                    .build();

            return getLeagueStandingsResponse;
        }

        throw new RuntimeException("Something Went Wrong!! Internal Server Error !!!");
    }
}
