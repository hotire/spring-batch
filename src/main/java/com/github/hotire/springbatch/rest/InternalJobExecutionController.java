package com.github.hotire.springbatch.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/jobs/{jobName}/execution")
@RestController
public class InternalJobExecutionController {

    @GetMapping
    public ResponseEntity<JobResource> execute(@PathVariable String jobName, @RequestParam Map<String, String> params) {
        log.info("jobName : {}", jobName);
        log.info("params : {}", params);
        return ResponseEntity.ok().build();
    }
}
