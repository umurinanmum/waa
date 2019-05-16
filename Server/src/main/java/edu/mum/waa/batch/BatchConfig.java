package edu.mum.waa.batch;


import edu.mum.waa.dto.AttendanceDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
import org.springframework.core.io.FileSystemResource;

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
    private ItemReader<ManualModel> itemReaderManualModel;

    @Autowired
    private ItemProcessor<BarcodeModel, AttendanceDto> itemProcessor;

    @Autowired
    private ItemProcessor<ManualModel, AttendanceDto> itemProcessorManualModel;

    @Autowired
    private ItemWriter<AttendanceDto> itemWriter;


    @Bean
    protected Step step1()
    {
        return steps.get("step1").<BarcodeModel, AttendanceDto> chunk(10)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
    }


    @Bean
    protected Step step2()
    {
        return steps.get("step2").<ManualModel, AttendanceDto> chunk(10)
                .reader(itemReaderManualModel).processor(itemProcessorManualModel).writer(itemWriter).build();
    }

    @Bean(name = "barcodeJob")
    public Job barcodeJob(){
        return jobs.get("barcodeJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean(name = "manuelJob")
    public Job manualJob(){
        return jobs.get("manualJob")
                .incrementer(new RunIdIncrementer())
                .start(step2())
                .build();
    }



    @Bean
    public FlatFileItemReader<BarcodeModel> read(){

        FlatFileItemReader<BarcodeModel> reader = new FlatFileItemReader<>();

        reader.setResource(new FileSystemResource("/tmp/rest/attendance.csv"));

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


    @Bean
    public FlatFileItemReader<ManualModel> read2() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        FlatFileItemReader<ManualModel> reader = new FlatFileItemReader<>();

        reader.setResource(new FileSystemResource("/tmp/rest/manual.csv"));

        reader.setLineMapper(new DefaultLineMapper<ManualModel>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{
                                "date",
                                "studentId",
                                "fullName"
                        });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<ManualModel>() {
                    {
                        setTargetType(ManualModel.class);
                    }

                });

            }

        });

        return reader;
    }


}
