package com.codecool.series.springtestseries.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    @ElementCollection
    @Singular
    private List<String> prices;

    @ManyToMany
    @Singular
    @EqualsAndHashCode.Exclude
    private List<Show> shows;

    @Transient
    private Integer numOfPrices;

    public int getNumOfPrices() {
        if (this.numOfPrices == null) {
            this.numOfPrices = this.prices.size();
        }
        return numOfPrices;
    }

}
