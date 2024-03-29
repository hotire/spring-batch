package com.github.hotire.springbatch.reader.page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.data.AbstractPaginatedDataItemReader;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class DefaultPagingItemReader<T> extends AbstractPaginatedDataItemReader<T> {

    private final Function<PagingParam, List<T>> listProvider;

    private ExecutionContext context;

    public DefaultPagingItemReader(int pageSize, Function<PagingParam, List<T>> listProvider) {
        setPageSize(pageSize);
        this.listProvider = listProvider;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        super.open(executionContext);
        context = executionContext;
    }

    @Override
    protected Iterator<T> doPageRead() {
        return listProvider.apply(new PagingParam(context, page, pageSize))
                           .iterator();
    }

    @Data
    @RequiredArgsConstructor
    public static class PagingParam {

        private final ExecutionContext context;
        private final int page;
        private final int size;

        public int getOffset() {
            return page * size;
        }

        public Map<String, Object> toPagingMap() {
            Map<String, Object> params = new HashMap<>();
            params.put("offset", getOffset());
            params.put("size", getSize());
            return params;
        }
    }
}
