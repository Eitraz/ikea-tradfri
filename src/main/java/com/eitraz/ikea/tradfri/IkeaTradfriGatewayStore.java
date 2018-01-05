package com.eitraz.ikea.tradfri;

import org.eclipse.californium.scandium.dtls.pskstore.PskStore;

import java.net.InetSocketAddress;

public class IkeaTradfriGatewayStore implements PskStore {
    private final String identity;
    private final byte[] key;

    private IkeaTradfriGatewayStore(String identity, byte[] key) {
        this.identity = identity;
        this.key = key;
    }

    @Override
    public String getIdentity(InetSocketAddress inetAddress) {
        return identity;
    }

    @Override
    public byte[] getKey(String identity) {
        return key;
    }

    public static IkeaTradfriGatewayStore createRegisterApplicationStore(String secureKey) {
        return new IkeaTradfriGatewayStore("Client_identity", secureKey.getBytes());
    }

    public static IkeaTradfriGatewayStore applicationStore(String identity, String secureKey) {
        return new IkeaTradfriGatewayStore(identity, secureKey.getBytes());
    }
}
