package edu.mum.waa.batch;


import edu.mum.waa.entity.Attendance;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Value("input/record.csv")
    private Resource input;

    @Value("file:xml/output.xml")
    private Resource output;

    @Bean
    public ItemProcessor<BarcodeModel, Attendance> itemProcessor() {
        return new BarcodeProcessor();
    }

    //https://www.baeldung.com/introduction-to-spring-batch

}
