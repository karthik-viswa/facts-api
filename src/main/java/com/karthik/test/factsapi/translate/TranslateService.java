package com.karthik.test.factsapi.translate;

import com.karthik.test.factsapi.translate.exception.TranslationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TranslateService
{
    private TranslationProvider provider;

    @Value("${translate.api.key}")
    private String apiKey;

    public TranslateService()
    {
        provider = TranslationProviderFactory.getTranslationProvider(null);
    }

    public String translate(String text, String sourceLanguage, String targetLanguage) throws TranslationException
    {
        return provider.translate(text, sourceLanguage, targetLanguage, apiKey);
    }
}
