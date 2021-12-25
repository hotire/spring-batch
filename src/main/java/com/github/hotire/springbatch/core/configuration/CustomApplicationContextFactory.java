package com.github.hotire.springbatch.core.configuration;

import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @see org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
 * @see org.springframework.batch.core.configuration.support.ApplicationContextFactory
 * @see org.springframework.batch.core.configuration.support.AbstractApplicationContextFactory
 */
public class CustomApplicationContextFactory implements ApplicationContextFactory {
    @Override
    public ConfigurableApplicationContext createApplicationContext() {
        return null;
    }
}
