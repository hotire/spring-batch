package com.github.hotire.springbatch.reader.page;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

/**
 * @see org.springframework.batch.item.data.AbstractPaginatedDataItemReader
 */
public class ZeroOffSetItemReader<T, ID> extends AbstractItemStreamItemReader<T> {

    private final Function<Param<ID>, List<T>> resultsProvider;
    private final ZeroOffSetIdMapper<T, ID> idMapper;
    private final int pageSize;

    public ID lastId;
    protected Iterator<T> results;


    public ZeroOffSetItemReader(Function<Param<ID>, List<T>> resultsProvider, ZeroOffSetIdMapper<T, ID> idMapper, int pageSize, ID lastId) {
        this.resultsProvider = resultsProvider;
        this.pageSize = pageSize;
        this.lastId = lastId;
        this.idMapper = idMapper;
        setName(ZeroOffSetItemReader.class.getSimpleName());
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (results == null || !results.hasNext()) {

            results = doPageRead();

            if (results == null || !results.hasNext()) {
                return null;
            }
        }

        final T result = results.next();
        lastId = idMapper.getId(result);
        return result;
    }

    protected Iterator<T> doPageRead() {
        return resultsProvider.apply(new Param(lastId, pageSize)).iterator();
    }

    @Getter
    @RequiredArgsConstructor
    public static class Param<ID> {
        private final ID lastSequenceId;
        private final int pageSize;
    }
}
