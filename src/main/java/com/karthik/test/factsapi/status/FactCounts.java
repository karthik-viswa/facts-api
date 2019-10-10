package com.karthik.test.factsapi.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class FactCounts
{
    private int total;

    private int unique;
}
