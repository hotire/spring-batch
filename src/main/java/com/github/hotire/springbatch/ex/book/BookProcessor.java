package com.github.hotire.springbatch.ex.book;

import org.springframework.batch.item.ItemProcessor;

public class BookProcessor implements ItemProcessor<Book,Book> {
    @Override
    public Book process(Book item) throws Exception {
        return item;
    }
}
