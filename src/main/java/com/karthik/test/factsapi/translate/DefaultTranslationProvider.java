package com.karthik.test.factsapi.translate;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import com.karthik.test.factsapi.translate.exception.TranslationException;
import org.apache.commons.lang3.StringUtils;

public class DefaultTranslationProvider implements TranslationProvider
{

    @Override
    public String translate(final String text, final String sourceLanguage, final String targetLanguage, final String apiKey) throws TranslationException
    {
        if(StringUtils.isAnyBlank(text, sourceLanguage, targetLanguage))
        {
            throw new IllegalArgumentException("One or more parameters are empty or null");
        }

        GoogleAPI.setHttpReferrer("http://localhost:8080/facts/*");
        GoogleAPI.setKey(apiKey);

        Language sourceLang = Language.fromString(sourceLanguage);
        Language targetLang = Language.fromString(targetLanguage);

        validateLangsForTranslation(sourceLanguage, targetLanguage, sourceLang, targetLang);

        String translatedText;

        try
        {
            translatedText = Translate.DEFAULT.execute(text, Language.fromString(sourceLanguage), Language.fromString(targetLanguage));
        }
        catch (GoogleAPIException e)
        {
            throw new TranslationException(e);
        }

        return translatedText;
    }

    private void validateLangsForTranslation(final String sourceLanguage, final String targetLanguage, final Language sourceLang, final Language targetLang) throws TranslationException
    {
        if(sourceLang == null)
        {
            String message = String.format("Source language '%s' not recognised by translation provider. Cannot translate", sourceLanguage);
            throw new TranslationException(message);
        }

        if(targetLang == null)
        {
            String message = String.format("Target language '%s' not recognised by translation provider. Cannot translate", targetLanguage);
            throw new TranslationException(message);
        }
    }
}
