package edu.mum.waa.batch;

import edu.mum.waa.entity.Attendance;
import org.springframework.batch.item.ItemProcessor;

public class BarcodeProcessor implements ItemProcessor<BarcodeModel, Attendance> {

    @Override
    public Attendance process(BarcodeModel barcodeModel) throws Exception {
        return null;
    }
}
