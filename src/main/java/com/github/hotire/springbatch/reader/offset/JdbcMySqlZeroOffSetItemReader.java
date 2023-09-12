package com.github.hotire.springbatch.reader.offset;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

/**
 * @see org.springframework.batch.item.data.AbstractPaginatedDataItemReader
 * @see org.springframework.batch.item.database.AbstractPagingItemReader;
 */
@RequiredArgsConstructor
public class JdbcMySqlZeroOffSetItemReader<T, ID extends Comparable<ID>> extends AbstractItemStreamItemReader<T> {

    private final DataSource dataSource;
    private final String sql;
    private final int pageSize;
    private final IdMapper<T, ID> idMapper;
    private ID greaterThanId;
    private int current = 0;
    private int readCount = 0;
    private List<T> results = new ArrayList<>();
    @Override
    public T read()
        throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

    private void doReadPage() {
    }
}
