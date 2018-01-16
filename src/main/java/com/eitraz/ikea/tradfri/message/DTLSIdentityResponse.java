package com.eitraz.ikea.tradfri.message;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTLSIdentityResponse {
    @JsonAlias("9091")
    private String secureKey;

    @JsonAlias("9029")
    private String version;

    public String getSecureKey() {
        return secureKey;
    }

    public String getVersion() {
        return version;
    }
}
