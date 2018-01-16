package com.eitraz.ikea.tradfri;

import com.eitraz.ikea.tradfri.device.Device;
import com.eitraz.ikea.tradfri.device.DeviceFactory;
import com.eitraz.ikea.tradfri.device.DeviceFactoryException;
import com.eitraz.ikea.tradfri.device.IdentifiableDevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.pskstore.PskStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Gateway {
    private static Logger logger = LoggerFactory.getLogger(Gateway.class);

    public static final String DEVICES_PATH = "15001";

    private final String baseUri;
    private final DTLSConnector clientConnector;

    public Gateway(String host, int port, PskStore pskStore) {
        baseUri = String.format("coaps://%s:%d", host, port);

        // Client configuration
        DtlsConnectorConfig.Builder clientConfig = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));
        clientConfig.setPskStore(pskStore);
        clientConfig.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.TLS_PSK_WITH_AES_128_CCM_8});

        // Client connector
        clientConnector = new DTLSConnector(clientConfig.build(), null);
    }

    public CoapClient createClient(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;

        CoapClient client = new CoapClient();
        client.setTimeout(5000);
        client.setEndpoint(new CoapEndpoint(clientConnector, NetworkConfig.getStandard()));
        client.setURI(baseUri + path);

        return client;
    }

    public String get(String path) {
        CoapClient client = createClient(path);
        logger.info("GET: " + client.getURI());
        CoapResponse response = client.get(MediaTypeRegistry.APPLICATION_JSON);
        logger.info("Response: " + response.getResponseText());
        return response.getResponseText();
    }

    public <T> T get(String path, Class<T> responseType) {
        try {
            return new ObjectMapper().readValue(get(path), responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IdentifiableDevice> getDeviceIds() {
        int[] identifiers = get(DEVICES_PATH, int[].class);
        return Arrays.stream(identifiers)
                .mapToObj(id -> IdentifiableDevice.create(this, id))
                .collect(Collectors.toList());
    }

    public List<Device> getDevices() {
        return getDeviceIds().stream()
                .map(i -> (Device) getDevice(i))
                .collect(Collectors.toList());
    }

    public <T extends Device> T getDevice(int id) {
        return getDevice(IdentifiableDevice.create(this, id));
    }

    public <T extends Device> T getDevice(IdentifiableDevice device) {
        String json = get(device.getPath());
        try {
            return DeviceFactory.createDevice(this, json);
        } catch (DeviceFactoryException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(Resource resource) {
        CoapClient client = createClient(resource.getPath());
        String json = toJson(resource);
        logger.info("PUT: " + client.getURI() + ": " + json);
        CoapResponse response = client.put(json, MediaTypeRegistry.APPLICATION_JSON);
        logger.info("Response: " + response.getResponseText());
    }

    private static String toJson(Resource resource) {
        try {
            return new ObjectMapper().writeValueAsString(resource);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
