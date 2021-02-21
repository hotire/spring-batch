package com.github.hotire.springbatch.mybatis;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hotire.springbatch.AbstractJobConfig;

@Configuration
public class ProductJobConfig extends AbstractJobConfig {

    private ProductMapper productMapper;

    @Bean
    public Job productJob() {
        return registerJob(getJobBuilderFactory().get("productJob")
                                                 .start(productStep())
                                                 .build());
    }

    @Bean
    @JobScope
    public Step productStep() {
        return getStepBuilderFactory()
                .get("productStep")
                .<Product, Product>chunk(1000)
                .reader(Product::new)
                .writer(list -> {})
                .build();
    }

}
