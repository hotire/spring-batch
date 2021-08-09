package com.github.hotire.springbatch.jdbc;

import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class ResultSetDecorator implements ResultSet {

    public static final int FETCH_FORWARD = ResultSet.FETCH_FORWARD;
    public static final int FETCH_REVERSE = ResultSet.FETCH_REVERSE;
    public static final int FETCH_UNKNOWN = ResultSet.FETCH_UNKNOWN;

    @Delegate
    private final ResultSet delegate;
}
