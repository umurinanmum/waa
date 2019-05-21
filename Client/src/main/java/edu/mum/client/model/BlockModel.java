package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter
public class BlockModel {

    private Integer id;

    //@NotEmpty
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    //@NotEmpty
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;

    private int cancelledDays;

    @NotEmpty
<<<<<<< HEAD
    @Size(min = 2, max = 50, message = "${Size.name}")
    private String name;
=======
    @Size(min = 2, max = 50, message = "${Size.lastName}")
    private String name="umur";
>>>>>>> master

}
