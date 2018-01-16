package com.eitraz.ikea.tradfri.message;

import com.eitraz.ikea.tradfri.Resource;
import com.fasterxml.jackson.annotation.JsonAlias;

public class DTLSIdentityRequest implements Resource {
    @JsonAlias("9090")
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getPath() {
        return "15011/9063";
    }
}
