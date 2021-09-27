package com.github.hotire.springbatch.partitioner;

import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.item.ExecutionContext;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomPartitioner implements Partitioner {

    @Getter
    private final SimplePartitioner delegate;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        return getDelegate().partition(gridSize);
    }
}
