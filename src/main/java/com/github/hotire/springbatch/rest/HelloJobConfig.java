package com.github.hotire.springbatch.rest;

import com.github.hotire.springbatch.AbstractJobConfig;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HelloJobConfig extends AbstractJobConfig {

    private final Parameter parameter;

    @Getter
    @Setter
    public static class Parameter {
        private String name;

        @PostConstruct
        public void init() {
            log.info("[HelloJobConfig-Parameter] init");
        }
    }

    @PostConstruct
    public void init() {
        log.info("[HelloJobConfig] init");
    }

    @Bean
    public Job helloJob() {
        return registerJob(getJobBuilderFactory().get("helloJob")
                                                 .preventRestart()
                                                 .incrementer(new RunIdIncrementer())
                                                 .start(helloStep(null))
                                                 .build());
    }

    @Bean
    @JobScope
    public Step helloStep(@Value("#{jobParameters['sampleProperty']}") String sampleProperty){
        log.info("[HelloJobConfig] parameter name : {}", parameter.getName());
        return getStepBuilderFactory().get("helloStep")
                                      .tasklet((stepContribution, chunkContext) -> {
                                          log.info("hello");
                                          log.info("sampleProperty : {}", sampleProperty);
                                          return RepeatStatus.FINISHED;
                                      }).build();
    }


    @Bean
    @JobScope
    public Parameter parameter() {
        return new Parameter();
    }
}
