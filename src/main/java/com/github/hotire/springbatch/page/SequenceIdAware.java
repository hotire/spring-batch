package com.github.hotire.springbatch.page;

@FunctionalInterface
public interface SequenceIdAware {
    long getSequenceId();
}
