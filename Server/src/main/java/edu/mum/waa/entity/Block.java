package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SequenceGenerator(name="seqBlock", initialValue=5)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqBlock")
    private long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;

    private String name;

}
