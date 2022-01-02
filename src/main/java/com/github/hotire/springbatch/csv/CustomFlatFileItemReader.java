package com.github.hotire.springbatch.csv;

import org.springframework.batch.item.file.FlatFileItemReader;

/**
 * @see FlatFileItemReader
 * @see org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
 */
public class CustomFlatFileItemReader<T> extends FlatFileItemReader<T> {
}
