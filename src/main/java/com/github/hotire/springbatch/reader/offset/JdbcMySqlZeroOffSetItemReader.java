package com.github.hotire.springbatch.reader.offset;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

/**
 * @see org.springframework.batch.item.data.AbstractPaginatedDataItemReader
 * @see org.springframework.batch.item.database.AbstractPagingItemReader;
 * @see
 */
public class JdbcMySqlZeroOffSetItemReader<T> implements AbstractItemStreamItemReader<T> {

    @Override
    public T read()
        throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
