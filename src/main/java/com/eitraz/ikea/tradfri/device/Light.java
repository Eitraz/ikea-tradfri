package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

/**
 * https://github.com/IPSO-Alliance/pub/blob/master/docs/IPSO-Smart-Objects.pdf
 */
public class Light extends Device {
    @JsonAlias("3311")
    private List<LightInfo> lightInfo;

    public List<LightInfo> getLightInfo() {
        return lightInfo;
    }

    public boolean isDimmable() {
        return lightInfo.stream().anyMatch(info -> info.getDimmer().isPresent());
    }

    @Override
    public String toString() {
        return "Light{" +
                "lightInfo=" + lightInfo +
                "} " + super.toString();
    }
}
