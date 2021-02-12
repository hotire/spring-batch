package com.github.hotire.springbatch.rest;

import org.springframework.batch.core.Job;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobResource {
    private final Job job;
}
