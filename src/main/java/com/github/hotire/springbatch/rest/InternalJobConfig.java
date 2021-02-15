package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class InternalJobConfig {

    @Bean
    public AsyncJobLauncher asyncJobLauncher(JobRepository jobRepository) {
        final AsyncSimpleJobLauncher asyncSimpleJobLauncher = new AsyncSimpleJobLauncher();
        asyncSimpleJobLauncher.setTaskExecutor(asyncTaskExecutor());
        asyncSimpleJobLauncher.setJobRepository(jobRepository);
        return asyncSimpleJobLauncher;
    }

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(30);
        executor.setAwaitTerminationSeconds(10);
        executor.setAllowCoreThreadTimeOut(true);
        return executor;
    }

}
