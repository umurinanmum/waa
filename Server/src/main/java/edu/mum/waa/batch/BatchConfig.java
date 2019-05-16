package edu.mum.waa.batch;


import edu.mum.waa.dto.AttendanceDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
    private ItemProcessor<BarcodeModel, AttendanceDto> itemProcessor;

    @Autowired
    private ItemWriter<AttendanceDto> itemWriter;


    @Bean
    protected Step step1()
    {
        return steps.get("step1").<BarcodeModel, AttendanceDto> chunk(10)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
    }

    @Bean(name = "waaBatch")
    public Job job(@Qualifier("step1") Step step1) {
        return jobs.get("waaBatch").start(step1).build();
    }

    //https://www.baeldung.com/introduction-to-spring-batch


    @Bean
    public FlatFileItemReader<BarcodeModel> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        FlatFileItemReader<BarcodeModel> reader = new FlatFileItemReader<>();

        reader.setResource(new ClassPathResource("attendance.csv"));

        reader.setLineMapper(new DefaultLineMapper<BarcodeModel>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{
                                "barcode",
                                "date",
                                "time",
                                "location"
                        });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<BarcodeModel>() {
                    {
                        setTargetType(BarcodeModel.class);
                    }

                });

            }

        });

        return reader;
    }


}
