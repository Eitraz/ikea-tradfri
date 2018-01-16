package com.eitraz.ikea.tradfri.device;

import com.eitraz.ikea.tradfri.Gateway;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class DeviceFactory {
    private static final int DEVICE_TYPE_REMOTE = 0;
    private static final int DEVICE_TYPE_LIGHT = 2;
    private static final int DEVICE_TYPE_MOTION_SENSOR = 4;

    private DeviceFactory() {
    }

    public static <T extends Device> T createDevice(Gateway gateway, String json) throws DeviceFactoryException {
        return createTypedDevice(gateway, json, parseDevice(gateway, json, Device.class));
    }

    private static <T extends Device> T parseDevice(Gateway gateway, String json, Class<T> type) throws DeviceFactoryException {
        try {
            T t = new ObjectMapper().readValue(json, type);
            t.setGateway(gateway);
            return t;
        } catch (IOException e) {
            throw new DeviceFactoryException("Unable to parse device from JSON", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Device> T createTypedDevice(Gateway gateway, String json, Device device) throws DeviceFactoryException {
        // Remote
        if (device.getType() == DEVICE_TYPE_REMOTE) {
            return (T) parseDevice(gateway, json, Remote.class);
        }
        // Light
        else if (device.getType() == DEVICE_TYPE_LIGHT) {
            return (T) parseTypedLightDevice(gateway, json, parseDevice(gateway, json, Light.class));
        }
        // Motion sensor
        else if (device.getType() == DEVICE_TYPE_MOTION_SENSOR) {
            return (T) parseDevice(gateway, json, MotionSensor.class);
        }
        // Return device
        else {
            return (T) device;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Light> T parseTypedLightDevice(Gateway gateway, String json, Light light) throws DeviceFactoryException {
        // Dimmable
        if (light.isDimmable()) {
            return (T) parseDevice(gateway, json, DimmableLight.class);
        }
        // Return light
        else {
            return (T) light;
        }
    }
}
