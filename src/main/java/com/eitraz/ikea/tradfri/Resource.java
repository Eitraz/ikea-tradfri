package com.eitraz.ikea.tradfri;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Resource {
    @JsonIgnore
    String getPath();
}
