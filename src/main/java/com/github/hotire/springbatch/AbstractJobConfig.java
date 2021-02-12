package com.github.hotire.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobRegistry registry;

    public Job registerJob(JobRegistry jobRegistry, Job job) {
        jobRegistry.unregister(job.getName());
        try {
            jobRegistry.register(new JobFactory() {
                @Override
                public Job createJob() {
                    return job;
                }

                @Override
                public String getJobName() {
                    return job.getName();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Could not create " + job.getName(), e);
        }
        return job;
    }
}
