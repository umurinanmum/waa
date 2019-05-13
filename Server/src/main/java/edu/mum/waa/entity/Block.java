package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate startDate;

    private LocalDate endDate;


    @OneToMany(mappedBy = "block")
    //@JoinColumn(name="block_id")
    private List<Session> sessions;


    private int canceledDays=0;




}
