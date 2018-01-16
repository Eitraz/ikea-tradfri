package com.eitraz.ikea.tradfri.message;

import com.eitraz.ikea.tradfri.Resource;
import com.eitraz.ikea.tradfri.device.IdentifiableDevice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DimLightRequest implements Resource {
    private transient IdentifiableDevice device;

    @JsonProperty(value = "3311")
    private final List<DimLightInfo> lightInfo = new ArrayList<>();

    public DimLightRequest(IdentifiableDevice device, byte value) {
        this.device = device;
        lightInfo.add(new DimLightInfo(value & 0xFF));
    }

    public List<DimLightInfo> getLightInfo() {
        return lightInfo;
    }

    @Override
    public String getPath() {
        return device.getPath();
    }

    public class DimLightInfo {
        // Range 0-255
        @JsonProperty(value = "5851")
        private Integer dimmer;

        DimLightInfo(Integer dimmer) {
            this.dimmer = dimmer;
        }

        public Integer getDimmer() {
            return dimmer;
        }
    }
}
