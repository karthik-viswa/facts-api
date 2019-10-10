package com.karthik.test.factsapi.controller;

import com.karthik.test.factsapi.FactsLoader;
import com.karthik.test.factsapi.model.FactRepository;
import com.karthik.test.factsapi.status.FactCounts;
import com.karthik.test.factsapi.status.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StatusController
{
    @Autowired
    FactsLoader loader;

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus()
    {
        StatusResponse response = new StatusResponse(loader.getStatus(), new FactCounts(loader.getFetchCount(), FactRepository.getInstance().getNumberOfFacts()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
