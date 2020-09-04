package com.github.hotire.springbatch.book;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class BookWriter implements ItemWriter<Book> {
    @Override
    public void write(List<? extends Book> items) throws Exception {
        // TODO
    }
}
