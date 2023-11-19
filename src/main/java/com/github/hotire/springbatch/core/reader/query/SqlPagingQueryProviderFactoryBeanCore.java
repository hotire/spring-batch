package com.github.hotire.springbatch.core.reader.query;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.item.database.support.AbstractSqlPagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.support.DatabaseType;

/**
 * @see SqlPagingQueryProviderFactoryBean
 */
public class SqlPagingQueryProviderFactoryBeanCore {

    /**
     * @see SqlPagingQueryProviderFactoryBean#providers
     */
    private Map<DatabaseType, AbstractSqlPagingQueryProvider> providers = new HashMap<>();

}
