package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.task.AsyncTaskExecutor;

public interface AsyncJobLauncher extends JobLauncher {
    void setAsyncTaskExecutor(AsyncTaskExecutor taskExecutor);
}
