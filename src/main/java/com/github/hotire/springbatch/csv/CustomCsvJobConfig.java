package com.github.hotire.springbatch.csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.github.hotire.springbatch.AbstractJobConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomCsvJobConfig extends AbstractJobConfig {

    @Bean
    public Job csvJob() {
        return registerJob(getJobBuilderFactory().get("csvJob")
                                                 .incrementer(new RunIdIncrementer())
                                                 .start(csvStep())
                                                 .build());
    }

    @Bean
    public Step csvStep() {
        return getStepBuilderFactory().get("csvStep")
                                      .chunk(10000)
                                      .reader(new FlatFileItemReaderBuilder<CustomCsv>().name("csvReader")
                                                                                       .resource(new ClassPathResource("custom.csv"))
                                                                                       .linesToSkip(1)
                                                                                       .lineMapper(defaultLineMapper())
                                                                                       .build())
                                      .writer(items -> log.info("items size: {}", items.size()))
                                      .faultTolerant()
                                      .skip(FlatFileParseException.class)
                                      .skipPolicy(new AlwaysSkipItemSkipPolicy(){
                                          @Override
                                          public boolean shouldSkip(Throwable t, int skipCount) {
                                              log.error(t.getMessage());

                                              if (t instanceof FlatFileParseException) {
                                                  log.error(((FlatFileParseException) t).getInput());
                                              }

                                              log.error("skipCount {}", skipCount);
                                              return true;
                                          }
                                      })
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
