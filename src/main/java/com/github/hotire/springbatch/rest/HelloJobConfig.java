package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hotire.springbatch.AbstractJobConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class HelloJobConfig extends AbstractJobConfig {
    @Bean
    public Job helloJob() {
        return registerJob(getJobBuilderFactory().get("helloJob")
                                                 .preventRestart()
                                                 .incrementer(new RunIdIncrementer())
                                                 .start(helloStep(null))
                                                 .build());
    }

    @Bean
    @JobScope
    public Step helloStep(@Value("#{jobParameters['sampleProperty']}") String sampleProperty){
        return getStepBuilderFactory().get("helloStep")
                                      .tasklet((stepContribution, chunkContext) -> {
                                          log.info("hello");
                                          log.info("sampleProperty : {}", sampleProperty);
                                          return RepeatStatus.FINISHED;
                                      }).build();
    }
}
