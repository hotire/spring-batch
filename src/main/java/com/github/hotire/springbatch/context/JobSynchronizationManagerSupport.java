package com.github.hotire.springbatch.context;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.jsr.configuration.support.BatchPropertyContext;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.core.scope.context.SynchronizationManagerSupport;

/**
 * @see org.springframework.batch.core.scope.context.JobSynchronizationManager
 */
public class JobSynchronizationManagerSupport extends SynchronizationManagerSupport<JobExecution, JobContext> {

    private static final JobSynchronizationManagerSupport delegate = new JobSynchronizationManagerSupport();

    @Override
    protected void close(JobContext context) {
        context.close();
    }

    @Override
    protected JobContext createNewContext(JobExecution execution, BatchPropertyContext propertyContext) {
        return new JobContext(execution);
    }

    public static SynchronizationManagerSupport<JobExecution, JobContext> getInstance() {
        return delegate;
    }
}
