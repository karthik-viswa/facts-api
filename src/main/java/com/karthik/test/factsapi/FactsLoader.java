package com.karthik.test.factsapi;

import com.karthik.test.factsapi.model.Fact;
import com.karthik.test.factsapi.model.FactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class FactsLoader implements ApplicationRunner
{
    @Value("${random.fact.url}")
    private String randomFactUrl;

    @Override
    public void run(final ApplicationArguments args) //throws Exception
    {
        FactRepository factRepository = FactRepository.getInstance();

        RestTemplate restTemplate = new RestTemplate();

        for(int i=0; i < 1000; i++)
        {
            Fact fact = restTemplate.getForObject("https://uselessfacts.jsph.pl/random.json", Fact.class);
            log.debug("Loaded fact: {}", fact);
            factRepository.addFact(fact);
        }

        factRepository.setLoaded(true);

        log.debug("Number of unique facts loaded: {}", factRepository.getNumberOfFacts());
    }
}
