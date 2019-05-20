package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@SequenceGenerator(name="seqEntry", initialValue=5)
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqEntry")
    private long id;

    private String name;

}
