package com.karthik.test.factsapi;

import com.karthik.test.factsapi.model.Fact;
import com.karthik.test.factsapi.model.FactRepository;
import com.karthik.test.factsapi.status.Status;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class FactsLoader implements ApplicationRunner
{
    @Value("${random.fact.url}")
    private String randomFactUrl;

    @Getter
    private int fetchCount;

    @Getter
    private Status status = Status.NOT_LOADED;

    @Override
    public void run(final ApplicationArguments args) //throws Exception
    {
        FactRepository factRepository = FactRepository.getInstance();

        RestTemplate restTemplate = new RestTemplate();

        log.info("Loading random facts...");
        status = Status.LOADING;

        for(fetchCount=0; fetchCount < 1000; fetchCount++)
        {
            try
            {
                Fact fact = restTemplate.getForObject("https://uselessfacts.jsph.pl/random.json", Fact.class);
                log.debug("Loaded fact: {}", fact);
                factRepository.addFact(fact);
            }
            catch(RestClientException e)
            {
                status = Status.ERROR;
                log.error("An exception occurred when attempting to fetch a random fact", e);
                return;
            }
        }

        status = Status.COMPLETED;

        log.info("Number of unique facts loaded: {}", factRepository.getNumberOfFacts());
    }
}
