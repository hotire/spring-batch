package com.github.hotire.springbatch.rest;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InternalJobService {
    private final JobRegistry jobRegistry;

    public Collection<Job> jobs() {
        return this.jobRegistry.getJobNames()
                               .stream()
                               .map(this::getJob)
                               .collect(Collectors.toSet());
    }

    protected Job getJob(String jobName) {
        try {
            return jobRegistry.getJob(jobName);
        } catch (NoSuchJobException e) {
            throw new RuntimeException(e);
        }
    }

}
