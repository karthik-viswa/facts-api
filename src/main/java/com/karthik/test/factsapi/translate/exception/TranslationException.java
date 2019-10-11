package com.karthik.test.factsapi.translate.exception;

public class TranslationException extends Exception
{
    public TranslationException()
    {
        super();
    }

    public TranslationException(Exception e)
    {
        super(e);
    }

    public TranslationException(String message)
    {
        super(message);
    }
}
