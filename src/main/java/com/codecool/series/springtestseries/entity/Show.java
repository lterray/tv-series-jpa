package com.codecool.series.springtestseries.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Show {
    @GeneratedValue
    @Id
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany(mappedBy = "shows")
    @Singular
    @EqualsAndHashCode.Exclude
    private List<Actor> actors;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @Singular
    @EqualsAndHashCode.Exclude
    private List<Episode> episodes;

}
