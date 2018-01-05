package com.eitraz.ikea.tradfri.device;

public class DeviceFactoryException extends Exception {
    public DeviceFactoryException(String message) {
        super(message);
    }

    public DeviceFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
