package com.github.hotire.springbatch.book;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;

public class BookReader extends JpaPagingItemReader<Book> {

    public BookReader(final EntityManagerFactory entityManagerFactory, final int pageSize, final String query) {
        setEntityManagerFactory(entityManagerFactory);
        setPageSize(pageSize);
        setQueryString(query);
    }
}
