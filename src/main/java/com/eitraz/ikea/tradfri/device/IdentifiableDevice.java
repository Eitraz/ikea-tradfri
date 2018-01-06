package com.eitraz.ikea.tradfri.device;

import com.eitraz.ikea.tradfri.IkeaTradfriGateway;
import com.eitraz.ikea.tradfri.Resource;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiableDevice implements Resource {
    @JsonAlias("9003")
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String getPath() {
        return IkeaTradfriGateway.DEVICES_PATH + "/" + getId();
    }

    @Override
    public String toString() {
        return "IdentifiableDevice{" +
                "id=" + id +
                '}';
    }

    public static IdentifiableDevice create(int id) {
        IdentifiableDevice device = new IdentifiableDevice();
        device.id = id;
        return device;
    }
}
