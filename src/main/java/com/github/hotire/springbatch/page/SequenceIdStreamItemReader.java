package com.github.hotire.springbatch.page;

import java.util.Iterator;
import java.util.function.Function;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @see org.springframework.batch.item.data.AbstractPaginatedDataItemReader
 */
public class SequenceIdStreamItemReader<T extends SequenceIdAware> extends AbstractItemStreamItemReader<T> {

    private final Function<SequenceIdStreamParam, Iterator<T>> resultsProvider;
    private final int pageSize;

    private final Long lastSequenceId;
    protected Iterator<T> results;

    public SequenceIdStreamItemReader(Function<SequenceIdStreamParam, Iterator<T>> resultsProvider, int pageSize) {
        this(resultsProvider, pageSize, 0L);
    }
    public SequenceIdStreamItemReader(Function<SequenceIdStreamParam, Iterator<T>> resultsProvider, int pageSize, Long lastSequenceId) {
        this.resultsProvider = resultsProvider;
        this.pageSize = pageSize;
        this.lastSequenceId = lastSequenceId;
        setName(SequenceIdStreamItemReader.class.getSimpleName());
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SequenceIdStreamParam {
    }
}
