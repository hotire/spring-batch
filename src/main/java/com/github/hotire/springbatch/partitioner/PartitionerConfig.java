package com.github.hotire.springbatch.partitioner;

import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class PartitionerConfig {
    private final JobBuilderFactory jobs;

    private final StepBuilderFactory steps;

    @Bean(name = "partitionerJob")
    public Job partitionerJob()
            throws UnexpectedInputException, MalformedURLException, ParseException {
        return jobs.get("partitioningJob")
                   .start(partitionStep())
                   .build();
    }

    @Bean
    public Step partitionStep() throws UnexpectedInputException, MalformedURLException, ParseException {
//        return steps.get("partitionStep")
//                    .partitioner("slaveStep", partitioner())
//                    .step(slaveStep())
//                    .taskExecutor(taskExecutor())
//                    .build();
        return null;
    }
}
