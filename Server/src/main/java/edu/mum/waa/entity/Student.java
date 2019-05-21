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
@SequenceGenerator(name="seqStudent", initialValue=10)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqStudent")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String barcode;
    @Column(name = "student_id")
    private String studentId;

    @ManyToOne ()
    @JsonIgnoreProperties
    @JsonIgnore
    private Entry entry;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_sections")
    @JsonIgnore
    private List<Section> sections;
}
