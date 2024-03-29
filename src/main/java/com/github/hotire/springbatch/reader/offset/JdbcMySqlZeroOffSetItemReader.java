package com.github.hotire.springbatch.reader.offset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.data.AbstractPaginatedDataItemReader;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @see AbstractPaginatedDataItemReader
 * @see AbstractPagingItemReader
 * @see JdbcPagingItemReader
 */
@RequiredArgsConstructor
public class JdbcMySqlZeroOffSetItemReader<T, ID extends Comparable<ID>> extends AbstractItemStreamItemReader<T> {

    private final DataSource dataSource;
    private final String sql;
    private final int pageSize;
    private final List<Object> parameterValues;
    private final IdMapper<T, ID> idMapper;
    private final RowMapper<T> rowMapper;
    private final String limitedSql;

//    private final String limitedSql = sql + " LIMIT " + pageSize;
//    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private final JdbcTemplate jdbcTemplate;

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
     * @see JdbcPagingItemReader#doReadPage()
     */
    private void doReadPage() {
        readCount++;
        results.clear();
        final Collection<T> result = jdbcTemplate.query(limitedSql, Stream.concat(Stream.of(greaterThanId), parameterValues.stream()).toArray(), rowMapper);

        if (result.isEmpty()) {
            return;
        }

        final ID lastId = idMapper.toId(results.get(result.size() -1));
        setGreaterThanId(lastId);
        results.addAll(result);
    }

     void setGreaterThanId(ID greaterThanId) {
        if (this.greaterThanId.compareTo(greaterThanId) <= 0 ) {
            throw new IllegalStateException("Cannot update greaterThanId(from " + this.greaterThanId + " to " + greaterThanId + ") , because greaterThanId should be incremented. " );
        }
        this.greaterThanId = greaterThanId;
    }
}
