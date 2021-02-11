package com.github.hotire.springbatch.book;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class BookWriter implements ItemWriter<Book> {
    @Override
    public void write(List<? extends Book> items) throws Exception {
        // TODO
    }
}
