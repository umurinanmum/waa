package edu.mum.waa.batch;

import edu.mum.waa.entity.Attendance;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class BarcodeProcessor implements ItemProcessor<BarcodeModel, Attendance> {

    @Override
    public Attendance process(BarcodeModel barcodeModel) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        Attendance attendance = new Attendance();
        attendance.setDateTime(LocalDate.parse(barcodeModel.getDate(),formatter));
        return attendance;
    }
}
