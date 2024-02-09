package com.github.hotire.springbatch.core.scope;

import org.springframework.batch.core.scope.BatchScopeSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @see BatchScopeSupport
 */
public abstract class BatchScopeSupportCore {

    /**
     * @see BatchScopeSupport#postProcessBeanFactory(ConfigurableListableBeanFactory) 
     */
    public void postProcessBeanFactory(
        ConfigurableListableBeanFactory beanFactory) throws BeansException {
        
    }

    /**
     * @see BatchScopeSupport#getTargetNamePrefix()
     */
    public abstract String getTargetNamePrefix();
}
