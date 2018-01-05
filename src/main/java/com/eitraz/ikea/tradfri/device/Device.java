package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Device extends IdentifiableDevice {
    @JsonAlias("9001")
    private String name;

    @JsonAlias("9002")
    private long createdAt;

    @JsonAlias("9020")
    private long lastSeen;

    @JsonAlias("5750")
    private int type;

    @JsonAlias("9019")
    private int reachable;

    @JsonAlias("3")
    private DeviceInfo deviceInfo;

    public String getName() {
        return name;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public int getType() {
        return type;
    }

    public int getReachable() {
        return reachable;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", lastSeen=" + lastSeen +
                ", type=" + type +
                ", reachable=" + reachable +
                ", deviceInfo=" + deviceInfo +
                "} " + super.toString();
    }
}
