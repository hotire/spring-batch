package com.github.hotire.springbatch.core.configuration;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;

public class CustomBatchConfigurer implements BatchConfigurer {
    @Override
    public JobRepository getJobRepository() throws Exception {
        return null;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        return null;
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
        return null;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        return null;
    }
}
