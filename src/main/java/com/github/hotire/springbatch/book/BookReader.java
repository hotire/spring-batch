package com.github.hotire.springbatch.book;

import org.springframework.batch.item.database.JpaPagingItemReader;

public class BookReader extends JpaPagingItemReader<Book> {
    public BookReader(int pageSize) {
        setPageSize(pageSize);
    }
}
