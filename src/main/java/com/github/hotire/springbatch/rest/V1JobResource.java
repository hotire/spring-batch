package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.Job;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class V1JobResource implements JobResource{
    @JsonIgnore
    private final Job job;

    @Override
    public String getJobName() {
        return job.getName();
    }
}
