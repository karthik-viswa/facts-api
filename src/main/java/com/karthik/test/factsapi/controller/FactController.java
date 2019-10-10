package com.karthik.test.factsapi.controller;

import com.karthik.test.factsapi.model.Fact;
import com.karthik.test.factsapi.model.FactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@RestController
public class FactController
{
    @GetMapping("/facts")
    public Set<String> getFactIds()
    {
       return FactRepository.getInstance().getFactIds();
    }

    @GetMapping("/facts/{id}")
    public ResponseEntity<Fact> getFactById(@PathVariable String id)
    {
        Optional<Fact> fact = FactRepository.getInstance().getFactById(id);

        if(!fact.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fact with id " + id + " not found");
        }

        return new ResponseEntity<>(fact.get(), HttpStatus.OK);
    }
}
