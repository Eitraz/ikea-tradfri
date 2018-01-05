package com.eitraz.ikea.tradfri;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.pskstore.PskStore;

import java.net.InetSocketAddress;

public class IkeaTradfriGateway {
    private final String baseUri;
    private final DTLSConnector clientConnector;

    public IkeaTradfriGateway(String baseUri, PskStore pskStore) {
        this.baseUri = baseUri;

        // Client configuration
        DtlsConnectorConfig.Builder clientConfig = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));
        clientConfig.setPskStore(pskStore);
        clientConfig.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.TLS_PSK_WITH_AES_128_CCM_8});

        // Client connector
        clientConnector = new DTLSConnector(clientConfig.build(), null);
    }

    public CoapClient createClient(String path) {
        CoapClient client = new CoapClient();
        client.setTimeout(5000);
        client.setEndpoint(new CoapEndpoint(clientConnector, NetworkConfig.getStandard()));
        client.setURI(baseUri + path);

        return client;
    }
}
