package com.eitraz.ikea.tradfri.device;

/**
 * http://www.openmobilealliance.org/tech/profiles/LWM2M_Device-v1_0.xml
 */
public enum PowerSource {
    DC_POWER(0),
    INTERNAL_BATTERY(1),
    EXTERNAL_BATTERY(2),
    BATTERY(3), // not in spec, used by remote
    POWER_OVER_ETHERNET(4),
    USB(5),
    AC_MAINS_POWER(6),
    SOLAR(7);

    private final int code;

    PowerSource(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
