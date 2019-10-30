package com.codecool.series.springtestseries;

import com.codecool.series.springtestseries.entity.Actor;
import com.codecool.series.springtestseries.entity.Episode;
import com.codecool.series.springtestseries.entity.Genre;
import com.codecool.series.springtestseries.entity.Show;
import com.codecool.series.springtestseries.repository.ActorRepository;
import com.codecool.series.springtestseries.repository.EpisodeRepository;
import com.codecool.series.springtestseries.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringTestSeriesApplication {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringTestSeriesApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Show got = Show.builder()
                    .name("Game of thrones")
                    .genre(Genre.FANTASY)
                    .releaseDate(LocalDate.of(2011, 04, 17))
                    .build();

            showRepository.save(got);

            Actor peterDinklage = Actor.builder()
                    .name("Peter Dinklage")
                    .show(got)
                    .price("Oscar winner 2011")
                    .price("Oscar winner 2013")
                    .price("Oscar winner 2015")
                    .build();

            List<Episode> episodes = IntStream.range(1, 10).boxed()
                    .map(integer -> Episode.builder().name("Season 1 Episode " + integer).show(got).build())
                    .collect(Collectors.toList());


            actorRepository.save(peterDinklage);
            episodeRepository.saveAll(episodes);
        };
    }
}
