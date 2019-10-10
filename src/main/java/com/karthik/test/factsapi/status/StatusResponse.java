package com.karthik.test.factsapi.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusResponse
{
    Status status;

    FactCounts facts;
}
