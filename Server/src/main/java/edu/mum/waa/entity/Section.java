package edu.mum.waa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    private Block block;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JsonIgnore
    private Faculty faculty;

    @ManyToMany(mappedBy = "sections", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> studentList;



    private int canceledDays = 0;

    private int availableDays = 22;


}
