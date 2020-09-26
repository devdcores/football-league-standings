package com.football.standings.footballleaguestandings.service.validate;

import com.football.standings.footballleaguestandings.exception.CountryNotFoundException;
import com.football.standings.footballleaguestandings.exception.LeagueNotFoundException;
import com.football.standings.footballleaguestandings.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationService {

    public void validateGetLeagueStandingRequest(String countryName, String leagueName, String teamName) {

        if (StringUtils.isEmpty(countryName)) {
            throw new CountryNotFoundException("Not a valid country!");
        }

        if (StringUtils.isEmpty(leagueName)) {
            throw new LeagueNotFoundException("Not a valid League!");
        }

        if (StringUtils.isEmpty(teamName)) {
            throw new TeamNotFoundException("Not a valid Team!");
        }

    }
}
