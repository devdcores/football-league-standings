FROM winamd64/openjdk:8-jre

EXPOSE 8080

COPY target/football-league-standings-0.0.1-SNAPSHOT.jar football-league-standings-0.0.1-SNAPSHOT.jar

CMD java -jar /football-league-standings-0.0.1-SNAPSHOT.jar