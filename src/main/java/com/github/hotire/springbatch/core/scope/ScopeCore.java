package com.github.hotire.springbatch.core.scope;


import org.springframework.beans.factory.ObjectFactory;
import  org.springframework.beans.factory.config.Scope;

/**
 * @see Scope
 */
public interface ScopeCore {

    /**
     * @see Scope#get(String, ObjectFactory)
     */
    Object get(String name, ObjectFactory<?> objectFactory);
}
