package com.github.hotire.springbatch.rest;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;

public interface JobExecutionResource {
    String getJobName();

    JobParameters getJobParameters();

    Date getEndTime();

    Date getStartTime();

    BatchStatus getStatus();

    Date getCreateTime();

    Date getLastUpdated();
}
