package edu.mum.waa.batch;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BarcodeModel {

    private String barcode;
    private String date;
    private String time;
    private String location;

}
