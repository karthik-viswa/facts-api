package com.karthik.test.factsapi.translate;

import com.karthik.test.factsapi.translate.exception.TranslationException;

public interface TranslationProvider
{
    String translate(String text, String sourceLanguage, String targetLanguage, String apiKey) throws TranslationException;
}
