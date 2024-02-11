package com.github.hotire.springbatch.reader.page;

@FunctionalInterface
public interface ZeroOffSetIdMapper<T, ID> {
    ID getId(T item);
}
