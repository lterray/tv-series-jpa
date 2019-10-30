package com.codecool.series.springtestseries.repository;

import com.codecool.series.springtestseries.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}