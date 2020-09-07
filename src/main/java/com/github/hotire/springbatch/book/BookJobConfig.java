package com.github.hotire.springbatch.book;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BookJobConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    @StepScope
    public BookReader bookReader() {
        return new BookReader(entityManagerFactory, 100, "SELECT b FROM Book b");
    }

    @Bean
    @StepScope
    public BookWriter bookWriter() {
        return new BookWriter();
    }

    @Bean
    @StepScope
    public BookProcessor bookProcessor() {
        return new BookProcessor();
    }
}
