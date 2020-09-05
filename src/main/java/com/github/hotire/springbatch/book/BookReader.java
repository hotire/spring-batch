package com.github.hotire.springbatch.book;

import org.springframework.batch.item.database.JpaPagingItemReader;

import javax.persistence.EntityManagerFactory;

public class BookReader extends JpaPagingItemReader<Book> {

    public BookReader(final EntityManagerFactory entityManagerFactory, final int pageSize, final String query) {
        setEntityManagerFactory(entityManagerFactory);
        setPageSize(pageSize);
        setQueryString(query);
    }
}
