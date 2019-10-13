package com.karthik.test.factsapi.controller;

import com.karthik.test.factsapi.model.Fact;
import com.karthik.test.factsapi.model.FactRepository;
import com.karthik.test.factsapi.translate.TranslateService;
import org.apache.commons.lang3.StringUtils;
import com.karthik.test.factsapi.translate.exception.TranslationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@RestController
public class FactController
{
    @Autowired
    TranslateService translateService;

    @GetMapping("/facts")
    public Set<String> getFactIds()
    {
       return FactRepository.getInstance().getFactIds();
    }

    @GetMapping(value = "/facts/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Fact> getFactById(@PathVariable String id, @RequestParam(required = false) String lang)
    {
        Optional<Fact> fact = FactRepository.getInstance().getFactById(id);

        if(!fact.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fact with id " + id + " not found");
        }

        Fact foundFact = fact.get();

        if(StringUtils.isBlank(lang) || lang.equals(foundFact.getLanguage()))
        {
            return new ResponseEntity<>(foundFact, HttpStatus.OK);
        }

        String translatedText;

        try
        {
            translatedText = translateService.translate(foundFact.getText(), foundFact.getLanguage(), lang);
        }
        catch (TranslationException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        Fact translatedFact = createTranslatedFact(foundFact, translatedText, lang);

        return new ResponseEntity<>(translatedFact, HttpStatus.OK);
    }

    private Fact createTranslatedFact(Fact originalFact, String translatedText, String lang)
    {
        Fact translatedFact = new Fact();

        translatedFact.setId(originalFact.getId());
        translatedFact.setLanguage(lang);
        translatedFact.setText(translatedText);

        return translatedFact;
    }
}
