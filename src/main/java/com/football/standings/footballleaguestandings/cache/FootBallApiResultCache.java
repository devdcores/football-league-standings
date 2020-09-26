package com.football.standings.footballleaguestandings.cache;

import com.football.standings.footballleaguestandings.feign.FootballApiFeignClient;
import com.football.standings.footballleaguestandings.model.Country;
import com.football.standings.footballleaguestandings.model.League;
import com.football.standings.footballleaguestandings.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FootBallApiResultCache {

    @Autowired
    private FootballApiFeignClient footballApiFeignClient;

    //Using cache, country result will be same, it changes rarely.
    @Cacheable(value = "countriesCache")
    public Map<String, Integer> getCountries(String apiKey) {
        //Searching is map is faster than list. So converting to map.
        Map<String, Integer> countryMap = new HashMap<>();
        List<Country> countries = footballApiFeignClient.getCountries(apiKey);
        countries.stream().forEach(country -> {
            countryMap.put(country.getCountry_name(), country.getCountry_id());
        });
        return countryMap;
    }

    //Using cache, league result will be same, it changes rarely.
    @Cacheable(value = "countriesCache")
    public Map<String, Integer> getLeagues(int countryId, String apiKey) {
        Map<String, Integer> leagueMap = new HashMap<>();
        List<League> leagues = footballApiFeignClient.getLeagues(countryId, apiKey);
        leagues.stream().forEach(league->{
            leagueMap.put(league.getLeague_name(), league.getLeague_id());
        });
        return leagueMap;
    }

    public Map<String, Integer> getTeams(int leagueId, String apiKey) {
        Map<String, Integer> teamsMap = new HashMap<>();
        List<Team> teams = footballApiFeignClient.getTeams(leagueId, apiKey);
        teams.stream().forEach(team -> {
            teamsMap.put(team.getTeam_name(), team.getTeam_key());
        });
        return teamsMap;
    }
}
