package com.github.hotire.springbatch.rest;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class V1JobExecutionResource implements JobExecutionResource {
    @JsonIgnore
    private final JobExecution jobExecution;

    @Override
    public String getJobName() {
        return jobExecution.getJobInstance().getJobName();
    }

    @Override
    public JobParameters getJobParameters() {
        return jobExecution.getJobParameters();
    }

    @Override
    public Date getEndTime() {
        return jobExecution.getEndTime();
    }

    @Override
    public Date getStartTime() {
        return jobExecution.getStartTime();
    }

    @Override
    public BatchStatus getStatus() {
        return jobExecution.getStatus();
    }

    @Override
    public Date getCreateTime() {
        return jobExecution.getCreateTime();
    }

    @Override
    public Date getLastUpdated() {
        return jobExecution.getLastUpdated();
    }

    @Override
    public String toString() {
        return jobExecution.toString();
    }
}
