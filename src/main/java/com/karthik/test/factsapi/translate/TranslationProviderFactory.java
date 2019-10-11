package com.karthik.test.factsapi.translate;

import org.apache.commons.lang3.StringUtils;

class TranslationProviderFactory
{
    // could change this to accept an enum type to return the matching provider. for now, google is the only provider
    static TranslationProvider getTranslationProvider(String provider)
    {
        if(StringUtils.isBlank(provider) || provider.equals("google"))
        {
            return new DefaultTranslationProvider();
        }

        return new DefaultTranslationProvider();
    }
}
