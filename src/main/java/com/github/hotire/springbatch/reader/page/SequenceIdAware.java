package com.github.hotire.springbatch.reader.page;

@FunctionalInterface
public interface SequenceIdAware {
    long getSequenceId();
}
