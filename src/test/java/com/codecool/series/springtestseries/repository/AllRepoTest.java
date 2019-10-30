package com.codecool.series.springtestseries.repository;

import com.codecool.series.springtestseries.entity.Actor;
import com.codecool.series.springtestseries.entity.Show;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class AllRepoTest {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ActorRepository actorRepository;

    @Test
    public void saveUniqueFieldTwice() {
        Show got1 = Show.builder().name("got").build();
        Show got2 = Show.builder().name("got").build();
        showRepository.save(got1);

        assertThrows(DataIntegrityViolationException.class, () -> showRepository.saveAndFlush(got2));
    }

    @Test
    public void saveMultipleActors() {
        Show got = Show.builder().name("got").build();
        showRepository.save(got);

        actorRepository.save(Actor.builder()
                .name("Peter Dinklage")
                .show(got)
                .build());

        actorRepository.save(Actor.builder()
                .name("Peter Dinklage2")
                .show(got)
                .build());


        assertThat(actorRepository.findAll()).hasSize(2).allMatch(actor -> {
           return actor.getShows().stream().allMatch(show -> show.getName().equals("got"));
        });
    }

}
