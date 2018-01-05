package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiableDevice {
    @JsonAlias("9003")
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "IdentifiableDevice{" +
                "id=" + id +
                '}';
    }
}
