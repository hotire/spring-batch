package com.github.hotire.springbatch.jdbc;

import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class ResultSetDecorator implements ResultSet {

    @Delegate
    private final ResultSet delegate;
}
