package com.github.hotire.springbatch.cursor;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.SQLWarningException;

import lombok.RequiredArgsConstructor;

/**
 * @see org.springframework.batch.item.database.AbstractCursorItemReader
 */
@RequiredArgsConstructor
public class CustomAbstractCursorItemReader<T> extends JdbcCursorItemReader<T> {

    private final JdbcCursorItemReader<T> reader;

    @Override
    protected void handleWarnings(Statement statement) throws SQLWarningException,
                                                              SQLException {
        // error statement.getWarnings();
        // ignoring warnings
    }

}
