package com.football.standings.footballleaguestandings.config;

import com.football.standings.footballleaguestandings.exception.ClientErrorDecoder;
import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballApiFeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ClientErrorDecoder clientErrorDecoder() {
        return new ClientErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
