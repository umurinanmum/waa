package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BlockModel {

    private LocalDate startDate;

    private LocalDate endDate;

    private int cancelledDays=0;

}
