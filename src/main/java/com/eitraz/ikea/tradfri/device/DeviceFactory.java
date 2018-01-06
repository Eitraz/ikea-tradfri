package com.eitraz.ikea.tradfri.device;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class DeviceFactory {
    private static final int DEVICE_TYPE_REMOTE = 0;
    private static final int DEVICE_TYPE_LIGHT = 2;
    private static final int DEVICE_TYPE_MOTION_SENSOR = 4;

    private DeviceFactory() {
    }

    public static <T extends Device> T createDevice(String json) throws DeviceFactoryException {
        return createTypedDevice(json, parseDevice(json, Device.class));
    }

    private static <T extends Device> T parseDevice(String json, Class<T> type) throws DeviceFactoryException {
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (IOException e) {
            throw new DeviceFactoryException("Unable to parse device from JSON", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Device> T createTypedDevice(String json, Device device) throws DeviceFactoryException {
        // Remote
        if (device.getType() == DEVICE_TYPE_REMOTE) {
            return (T) parseDevice(json, Remote.class);
        }
        // Light
        else if (device.getType() == DEVICE_TYPE_LIGHT) {
            return (T) parseTypedLightDevice(json, parseDevice(json, Light.class));
        }
        // Motion sensor
        else if (device.getType() == DEVICE_TYPE_MOTION_SENSOR) {
            return (T) parseDevice(json, MotionSensor.class);
        }
        // Return device
        else {
            return (T) device;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Light> T parseTypedLightDevice(String json, Light light) throws DeviceFactoryException {
        // Dimmable
        if (light.isDimmable()) {
            return (T) parseDevice(json, DimmableLight.class);
        }
        // Return light
        else {
            return (T) light;
        }
    }
}
