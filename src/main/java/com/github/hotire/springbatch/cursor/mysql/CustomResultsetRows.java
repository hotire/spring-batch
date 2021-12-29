package com.github.hotire.springbatch.cursor.mysql;

import com.mysql.cj.protocol.ResultsetRows;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @see com.mysql.cj.protocol.a.result.ResultsetRowsStatic
 * @see com.mysql.cj.protocol.a.result.ResultsetRowsStreaming
 */
@RequiredArgsConstructor
public class CustomResultsetRows implements ResultsetRows {

    @Delegate
    private final ResultsetRows delegate;
}
