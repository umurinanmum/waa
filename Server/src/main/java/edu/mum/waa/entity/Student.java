package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String barcode;

    private String studentId;

    @ManyToOne
    private Entry entry;

    @ManyToMany
    private List<Section> sections;

    public long getId() {
        return id;
    }
}
