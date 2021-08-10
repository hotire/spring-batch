package com.github.hotire.springbatch.cursor;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import lombok.RequiredArgsConstructor;

/**
 * @see org.springframework.batch.item.database.AbstractCursorItemReader
 */
@RequiredArgsConstructor
public class CustomAbstractCursorItemReader<T> extends JdbcCursorItemReader<T> {

    private final JdbcCursorItemReader<T> reader;

}
