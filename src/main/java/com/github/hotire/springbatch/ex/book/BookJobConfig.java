package com.github.hotire.springbatch.ex.book;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BookJobConfig {

    private final EntityManagerFactory entityManagerFactory;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

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
