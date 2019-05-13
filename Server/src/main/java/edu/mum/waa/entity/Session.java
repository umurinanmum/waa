package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int startWeek;

    private int endWeek;

    private int startDay;

    private int endDay;

    @ManyToOne
    private Block block;

    @ManyToOne
    private Location location;

}
