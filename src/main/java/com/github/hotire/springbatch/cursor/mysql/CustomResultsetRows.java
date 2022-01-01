package com.github.hotire.springbatch.cursor.mysql;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.ResultsetRowsOwner;
import com.mysql.cj.result.Row;

import lombok.RequiredArgsConstructor;

/**
 * @see com.mysql.cj.protocol.a.result.ResultsetRowsStatic
 * @see com.mysql.cj.protocol.a.result.ResultsetRowsStreaming
 */
@RequiredArgsConstructor
public class CustomResultsetRows implements ResultsetRows {

    @Override
    public ResultsetRowsOwner getOwner() {
        return null;
    }

    @Override
    public boolean isAfterLast() {
        return false;
    }

    @Override
    public boolean isBeforeFirst() {
        return false;
    }

    @Override
    public void setOwner(ResultsetRowsOwner rs) {

    }

    @Override
    public boolean wasEmpty() {
        return false;
    }

    @Override
    public void setMetadata(ColumnDefinition columnDefinition) {

    }

    @Override
    public ColumnDefinition getMetadata() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Row next() {
        return null;
    }
}
