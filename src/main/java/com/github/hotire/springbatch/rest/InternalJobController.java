package com.github.hotire.springbatch.rest;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/v1/jobs")
@RestController
@RequiredArgsConstructor
public class InternalJobController {

    private final InternalJobService internalJobService;

    @GetMapping
    public ResponseEntity<Collection<JobResource>> jobs() {
        return ResponseEntity.ok(internalJobService.jobs()
                                                   .stream()
                                                   .map(V1JobResource::new)
                                                   .collect(Collectors.toSet()));
    }

    @GetMapping("/{jobName}/execution")
    public ResponseEntity<JobExecutionResource> execute(@PathVariable String jobName,
                                                        @RequestParam Map<String, Object> params,
                                                        @RequestParam(required = false) boolean async) {
        return ResponseEntity.ok(new V1JobExecutionResource(internalJobService.execute(jobName, params, async)));
    }
}
