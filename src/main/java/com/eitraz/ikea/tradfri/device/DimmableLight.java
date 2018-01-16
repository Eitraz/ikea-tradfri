package com.eitraz.ikea.tradfri.device;

import com.eitraz.ikea.tradfri.message.DimLightRequest;

public class DimmableLight extends Light {
    @Override
    public String toString() {
        return "DimmableLight{} " + super.toString();
    }

    public void dim(byte value) {
        getGateway().put(new DimLightRequest(this, value));
    }
}
