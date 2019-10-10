package com.karthik.test.factsapi.controller;

import com.karthik.test.factsapi.model.FactRepository;
import com.karthik.test.factsapi.status.FactCounts;
import com.karthik.test.factsapi.status.Status;
import com.karthik.test.factsapi.status.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StatusController
{

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus()
    {
        FactRepository factRepository = FactRepository.getInstance();

        Status status = factRepository.isLoaded()? Status.COMPLETED : Status.LOADING;

        StatusResponse response = new StatusResponse(status, new FactCounts(1000, factRepository.getNumberOfFacts()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
