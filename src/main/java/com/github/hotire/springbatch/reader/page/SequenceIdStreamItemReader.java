package com.github.hotire.springbatch.reader.page;

import java.util.Iterator;
import java.util.List;
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

    private final Function<SequenceIdStreamParam, List<T>> resultsProvider;
    private final int pageSize;

    public long lastSequenceId;
    protected Iterator<T> results;

    public SequenceIdStreamItemReader(Function<SequenceIdStreamParam, List<T>> resultsProvider, int pageSize) {
        this(resultsProvider, pageSize, 0L);
    }

    public SequenceIdStreamItemReader(Function<SequenceIdStreamParam, List<T>> resultsProvider, int pageSize, long lastSequenceId) {
        this.resultsProvider = resultsProvider;
        this.pageSize = pageSize;
        this.lastSequenceId = lastSequenceId;
        setName(SequenceIdStreamItemReader.class.getSimpleName());
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
        lastSequenceId = result.getSequenceId();
        return result;
    }

    protected Iterator<T> doPageRead() {
        return resultsProvider.apply(new SequenceIdStreamParam(lastSequenceId, pageSize)).iterator();
    }

    @Getter
    @RequiredArgsConstructor
    public static class SequenceIdStreamParam {
        private final long lastSequenceId;
        private final int pageSize;
    }
}
