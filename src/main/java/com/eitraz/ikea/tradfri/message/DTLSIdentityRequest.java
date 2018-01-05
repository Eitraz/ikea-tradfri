package com.eitraz.ikea.tradfri.message;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DTLSIdentityRequest {
    @JsonAlias("9090")
    private String identifier;
}
