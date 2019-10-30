package com.codecool.series.springtestseries.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Episode {

    @GeneratedValue
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Show show;

}
