package com.github.hotire.springbatch.rest;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import com.github.hotire.springbatch.JobUtils;

@Service
public class InternalJobService {
    private final JobRegistry jobRegistry;
    private final Map<Boolean, JobLauncher> jobLauncherMapByAsync;

    public InternalJobService(JobRegistry jobRegistry,
                              JobLauncher jobLauncher,
                              AsyncJobLauncher asyncJobLauncher) {
        this.jobRegistry = jobRegistry;
        this.jobLauncherMapByAsync = Map.of(true, asyncJobLauncher, false, jobLauncher);
    }

    public Collection<Job> jobs() {
        return this.jobRegistry.getJobNames()
                               .stream()
                               .map(this::getJob)
                               .collect(Collectors.toList());
    }

    public JobExecution execute(final String jobName, final Map<String, Object> params) {
        return execute(jobName, params, false);
    }

    public JobExecution execute(final String jobName, final Map<String, Object> params, boolean async) {
        try {
            final Job job = jobRegistry.getJob(jobName);
            final JobParameters jobParameters = JobUtils.convertRawToJobParams(params);
            return jobLauncherMapByAsync.get(async).run(job, jobParameters);
        } catch (NoSuchJobException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    protected Job getJob(String jobName) {
        try {
            return jobRegistry.getJob(jobName);
        } catch (NoSuchJobException e) {
            throw new RuntimeException(e);
        }
    }
    
}
