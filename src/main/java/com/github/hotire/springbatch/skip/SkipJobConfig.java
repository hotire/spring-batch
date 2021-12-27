package com.github.hotire.springbatch.skip;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.github.hotire.springbatch.AbstractJobConfig;
import com.github.hotire.springbatch.csv.CsvColumnDelimitedLineTokenizer;
import com.github.hotire.springbatch.csv.CustomCsv;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SkipJobConfig extends AbstractJobConfig {

    @Bean
    public Job skipJob() {
        return registerJob(getJobBuilderFactory().get("skipJob")
                                                 .incrementer(new RunIdIncrementer())
                                                 .start(limitStep())
                                                 .build());
    }

    @Bean
    @JobScope
    public Step limitStep() {
        return getStepBuilderFactory().get("limitStep")
                                      .chunk(10000)
                                      .reader(new FlatFileItemReaderBuilder<CustomCsv>().name("csvReader")
                                                                                        .resource(new ClassPathResource("custom.csv"))
                                                                                        .linesToSkip(1)
                                                                                        .lineMapper(defaultLineMapper())
                                                                                        .build())
                                      .writer(items -> log.info("items size: {}", items.size()))
                                      .faultTolerant()
                                      .build();
    }

    public DefaultLineMapper<CustomCsv> defaultLineMapper() {
        final DefaultLineMapper<CustomCsv> lineMapper = new DefaultLineMapper<>();

        final BeanWrapperFieldSetMapper<CustomCsv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CustomCsv.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(new CsvColumnDelimitedLineTokenizer(CustomCsv.class));
        return lineMapper;
    }
}

