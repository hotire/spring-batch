package com.github.hotire.springbatch.getting_started;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final EntityManagerFactory entityManagerFactory;
  private final UserRepository userRepository;

  @Bean
  public Job importUserJob() {
    return jobBuilderFactory.get("importUserJob")
                            .incrementer(new RunIdIncrementer())
                            .listener(listener())
                            .flow(step1())
                            .end()
                            .build();
  }

  @Bean
  @JobScope
  public JobCompletionNotificationListener listener() {
    return new JobCompletionNotificationListener(userRepository);
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
            .<User, User>chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
  }

  @Bean
  @StepScope
  public FlatFileItemReader<User> reader() {
    return new FlatFileItemReaderBuilder<User>()
            .name("userItemReader")
            .resource(new ClassPathResource("sample-data.csv"))
            .delimited()
            .names(new String[] { "firstName", "lastName" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
              setTargetType(User.class);
            }})
            .build();
  }

  @Bean
  @StepScope
  public UserItemProcessor processor() {
    return new UserItemProcessor();
  }

  @Bean
  @StepScope
  public JpaItemWriter<User> writer() {
    JpaItemWriter<User> jpaItemWriter = new JpaItemWriter<>();
    jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
    return jpaItemWriter;
  }

}
