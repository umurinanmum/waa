package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//@Table(indexes = {@Index(columnList = "student,dateTime", unique = true)})
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Student student;

    private LocalDate dateTime;

    @ManyToOne
    private Block block;

    @ManyToOne
    private Location location;

    private String time;

}
