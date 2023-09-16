package com.github.hotire.springbatch.reader.offset;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.batch.item.database.AbstractPagingItemReader;

/**
 * @see org.springframework.batch.item.data.AbstractPaginatedDataItemReader
 * @see AbstractPagingItemReader;
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

    /**
     * @see AbstractPagingItemReader#read()
     */
    @Override
    public T read()
        throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (results.isEmpty() || current >= pageSize) {
            doReadPage();
            if (current >= pageSize) {
                current = 0;
            }
        }

        int next = current++;
        if (next < results.size()) {
            return results.get(next);
        }
        else {
            return null;
        }
    }

    /**
     * @see AbstractPagingItemReader##doReadPage()
     */
    private void doReadPage() {
    }
}
