package com.football.standings.footballleaguestandings.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLeagueStandingsResponse {
    private int country_id;
    private String country_name;
    private int league_id;
    private String league_name;
    private int team_key;
    private String team_name;
    private int position;
}
