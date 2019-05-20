package edu.mum.client.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TmCheckAndRetreat {

    private long id;

    private Student student;

    private LocalDate localDateTime;

    private boolean isRetreat = false;
}
