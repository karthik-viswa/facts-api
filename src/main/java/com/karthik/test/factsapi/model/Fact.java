package com.karthik.test.factsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fact
{
    private String id;

    private String text;

    private String language;

    private String permalink;

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Fact fact = (Fact) o;
        return Objects.equals(id, fact.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
