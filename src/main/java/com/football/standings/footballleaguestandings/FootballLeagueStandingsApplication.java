package com.football.standings.footballleaguestandings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FootballLeagueStandingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballLeagueStandingsApplication.class, args);
	}

}
