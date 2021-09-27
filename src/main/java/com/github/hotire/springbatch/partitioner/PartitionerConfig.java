package com.github.hotire.springbatch.partitioner;

import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.RequiredArgsConstructor;

/**
 * https://github.com/eugenp/tutorials/blob/master/spring-batch/src/main/java/com/baeldung/batch/partitioner/SpringbatchPartitionConfig.java
 */
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
        return steps.get("partitionStep")
                    .partitioner("slaveStep", partitioner())
                    .step(slaveStep())
                    .taskExecutor(taskExecutor())
                    .build();
    }

    @Bean
    public Step slaveStep() throws UnexpectedInputException, MalformedURLException, ParseException {
        return null;
    }

    @Bean
    public Partitioner partitioner() {
        return new SimplePartitioner();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

}
