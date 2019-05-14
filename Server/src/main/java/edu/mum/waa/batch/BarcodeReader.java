package edu.mum.waa.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class BarcodeReader implements ItemReader<BarcodeModel> {


    @Override
    public BarcodeModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //read from file
        return null;
    }
}
