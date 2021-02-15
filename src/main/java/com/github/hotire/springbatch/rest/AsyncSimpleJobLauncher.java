package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.core.task.AsyncTaskExecutor;

public class AsyncSimpleJobLauncher extends SimpleJobLauncher implements AsyncJobLauncher {
    @Override
    public void setAsyncTaskExecutor(AsyncTaskExecutor taskExecutor) {
        setTaskExecutor(taskExecutor);
    }
}
