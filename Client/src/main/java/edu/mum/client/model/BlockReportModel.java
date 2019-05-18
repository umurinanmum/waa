package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
<<<<<<< HEAD:Client/src/main/java/edu/mum/client/model/BlockModel.java
public class BlockModel implements Serializable {
=======
public class BlockReportModel {
>>>>>>> master:Client/src/main/java/edu/mum/client/model/BlockReportModel.java

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private int cancelledDays=0;

    private String name;

}
