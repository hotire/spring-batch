package com.github.hotire.springbatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Bean
  public FlatFileItemReader<User> reader() {
    return new FlatFileItemReaderBuilder<User>()
      .name("userItemReader")
      .resource(new ClassPathResource("sample-data.csv"))
      .delimited()
      .names(new String[]{"firstName", "lastName"})
      .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
        setTargetType(User.class);
      }})
      .build();
  }

  @Bean
  public UserItemProcessor processor() {
    return new UserItemProcessor();
  }

  @Bean
  public JpaItemWriter<User> writer() {
    JpaItemWriter<User> jpaItemWriter = new JpaItemWriter<>();
    jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
    return jpaItemWriter;
  }

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importUserJob")
      .incrementer(new RunIdIncrementer())
      .listener(listener)
      .flow(step1)
      .end()
      .build();
  }

  @Bean
  public Step step1(JpaItemWriter<User> writer) {
    return stepBuilderFactory.get("step1")
      .<User, User> chunk(10)
      .reader(reader())
      .processor(processor())
      .writer(writer)
      .build();
  }
}
