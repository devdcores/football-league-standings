package com.football.standings.footballleaguestandings.feign;


import com.football.standings.footballleaguestandings.config.FootballApiFeignClientConfiguration;
import com.football.standings.footballleaguestandings.model.Country;
import com.football.standings.footballleaguestandings.model.League;
import com.football.standings.footballleaguestandings.model.Standings;
import com.football.standings.footballleaguestandings.model.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "footballApiFeignClient", url = "${api.football.url}", configuration = FootballApiFeignClientConfiguration.class)
public interface FootballApiFeignClient {

    @GetMapping(value = "?action=get_countries")
    List<Country> getCountries(@RequestParam("APIkey") String apiKey);

    @GetMapping(value = "?action=get_leagues")
    List<League> getLeagues(@RequestParam("country_id") int countryId,
                            @RequestParam("APIkey") String apiKey);

    @GetMapping(value = "?action=get_teams")
    List<Team> getTeams(@RequestParam("league_id") int leagueId,
                        @RequestParam("APIkey") String apiKey);

    @GetMapping(value = "?action=get_standings")
    List<Standings> getStandings(@RequestParam("league_id") int leagueId,
                                 @RequestParam("APIkey") String apiKey);

}