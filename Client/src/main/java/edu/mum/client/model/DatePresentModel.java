package edu.mum.client.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DatePresentModel {
    private LocalDate date;
    private boolean isPresent;
}
