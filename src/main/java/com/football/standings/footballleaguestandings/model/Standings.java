package com.football.standings.footballleaguestandings.model;

import lombok.Data;

@Data
public class Standings {
    private String country_name;
    private int league_id;
    private String league_name;
    private int team_id;
    private String team_name;
    private int overall_league_position;
}
