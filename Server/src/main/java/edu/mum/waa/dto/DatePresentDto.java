package edu.mum.waa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Getter
@Setter
public class DatePresentDto {

    private LocalDate date;
    private boolean isPresent;
}
