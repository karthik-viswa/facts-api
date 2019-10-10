package com.karthik.test.factsapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class FactRepository
{
    private Set<Fact> facts;

    @Getter
    @Setter
    private boolean loaded;

    private static FactRepository instance;

    private FactRepository()
    {
        facts = new HashSet<>();
    }

    public static FactRepository getInstance()
    {
        if(instance == null)
        {
            instance = new FactRepository();
        }

        return instance;
    }

    public void addFact(Fact fact)
    {
        facts.add(fact);
    }

    public int getNumberOfFacts()
    {
        return facts.size();
    }

    public Set<String> getFactIds()
    {
        return facts.stream().map(fact -> fact.getId())
                             .collect(Collectors.toSet());
    }

    public Optional<Fact> getFactById(String id)
    {
        return facts.stream().filter(fact -> fact.getId().equals(id))
                             .findFirst();
    }
}
