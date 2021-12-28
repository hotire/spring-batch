package com.github.hotire.springbatch.cursor.mysql;

import com.mysql.cj.protocol.Resultset;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @see com.mysql.cj.jdbc.result.ResultSetImpl
 *
 * ResultSetImpl use ResultsetRows
 */
@RequiredArgsConstructor
public class CustomResultSetImpl implements Resultset, java.sql.ResultSet {
    @Delegate
    private final Resultset resultset;
    @Delegate
    private final java.sql.ResultSet sqlResultSet;
}
