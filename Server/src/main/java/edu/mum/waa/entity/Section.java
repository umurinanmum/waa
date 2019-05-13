package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Section {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Block block;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Faculty faculty;


}
