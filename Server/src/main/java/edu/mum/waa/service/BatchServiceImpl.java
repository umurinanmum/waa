package edu.mum.waa.service;

import edu.mum.waa.service.interfaces.BatchService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public void startBarcode() throws Exception{
        JobParameters params = new JobParametersBuilder()
                .addString("barcodeJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }



}
