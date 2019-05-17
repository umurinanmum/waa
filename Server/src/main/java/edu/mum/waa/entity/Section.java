package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "sections")
    private List<Student> studentList;



    private int canceledDays = 0;

    private int availableDays = 22;


}
