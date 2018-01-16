package com.eitraz.ikea.tradfri.device;

import com.eitraz.ikea.tradfri.Gateway;
import com.eitraz.ikea.tradfri.Resource;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiableDevice implements Resource {
    private transient Gateway gateway;

    @JsonAlias("9003")
    private int id;

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPath() {
        return Gateway.DEVICES_PATH + "/" + getId();
    }

    @Override
    public String toString() {
        return "IdentifiableDevice{" +
                "id=" + id +
                '}';
    }

    public <T extends Device> T getDevice() {
        return gateway.getDevice(this);
    }

    public static IdentifiableDevice create(Gateway gateway, int id) {
        IdentifiableDevice device = new IdentifiableDevice();
        device.setGateway(gateway);
        device.id = id;
        return device;
    }
}
