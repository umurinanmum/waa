package edu.mum.waa.batch;


import edu.mum.waa.entity.Attendance;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ItemReader<BarcodeModel> itemReader;

    @Autowired
    private ItemProcessor<BarcodeModel, Attendance> itemProcessor;

    @Autowired
    private ItemWriter<Attendance> itemWriter;


    @Bean
    protected Step step1()
    {
        return steps.get("step1").<BarcodeModel, Attendance> chunk(10)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
    }

    @Bean(name = "waaBatch")
    public Job job(@Qualifier("step1") Step step1) {
        return jobs.get("waaBatch").start(step1).build();
    }

    //https://www.baeldung.com/introduction-to-spring-batch

}
