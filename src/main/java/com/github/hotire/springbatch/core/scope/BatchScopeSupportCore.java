package com.github.hotire.springbatch.core.scope;

import org.springframework.batch.core.scope.BatchScopeSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @see BatchScopeSupport
 */
public abstract class BatchScopeSupportCore implements Scope {


    /**
     * @see BatchScopeSupport#name
     */
    private String name;

    /**
     * @see BatchScopeSupport#postProcessBeanFactory(ConfigurableListableBeanFactory)
     */
    public void postProcessBeanFactory(
        ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(name, this);
    }

    /**
     * @see BatchScopeSupport#getTargetNamePrefix()
     */
    public abstract String getTargetNamePrefix();
}
