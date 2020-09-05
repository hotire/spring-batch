package com.github.hotire.springbatch.book;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BookJobConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public BookReader bookReader() {
        return new BookReader(entityManagerFactory, 100, "SELECT b FROM Book b");
    }
}
