package com.github.hotire.springbatch.reader.offset;

@FunctionalInterface
public interface IdMapper<T, ID> {
    ID toId(T item);
}
