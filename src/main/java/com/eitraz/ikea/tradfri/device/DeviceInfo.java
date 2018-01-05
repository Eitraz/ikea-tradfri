package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

/**
 * http://www.openmobilealliance.org/tech/profiles/LWM2M_Device-v1_0.xml
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInfo {
    @JsonAlias("0")
    private String manufacture;

    @JsonAlias("1")
    private String modelNumber;

    @JsonAlias("2")
    private String serialNumber;

    @JsonAlias("3")
    private String firmwareVersion;

    @JsonAlias("6")
    private PowerSource powerSource;

    @JsonAlias("9")
    private Integer batteryLevel;

    public String getManufacture() {
        return manufacture;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public Optional<Integer> getBatteryLevel() {
        return Optional.ofNullable(batteryLevel);
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "manufacture='" + manufacture + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", powerSource=" + powerSource +
                ", batteryLevel=" + batteryLevel +
                '}';
    }
}
