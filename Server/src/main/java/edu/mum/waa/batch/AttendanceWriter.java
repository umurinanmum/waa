package edu.mum.waa.batch;

import edu.mum.waa.entity.Attendance;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceWriter implements ItemWriter<Attendance> {

    // attendance repo

    @Override
    public void write(List<? extends Attendance> list) throws Exception {
        //for each call save
    }
}
