package com.github.hotire.springbatch.context;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.jsr.configuration.support.BatchPropertyContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.SynchronizationManagerSupport;

/**
 * @see org.springframework.batch.core.scope.context.StepSynchronizationManager
 */
public class StepSynchronizationManagerSupport extends SynchronizationManagerSupport<StepExecution, StepContext> {
    @Override
    protected void close(StepContext context) {
        context.close();
    }

    @Override
    protected StepContext createNewContext(StepExecution execution, BatchPropertyContext propertyContext) {
        return  Optional.ofNullable(propertyContext)
                        .map(it -> new StepContext(execution, it))
                        .orElseGet(() -> new StepContext(execution));
    }
}
