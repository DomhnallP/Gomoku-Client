package com.dopoil.gomokuclient.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;

@ConfigurationProperties("client")
@Component
public class GomokuClientProperties {

    private URL networkAddress;

    private URL serverAddress;

    public GomokuClientProperties() {

    }

    public GomokuClientProperties(final int width, final int height, final int winCondition) {

    }

    public URL getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(URL networkAddress) {
        this.networkAddress = networkAddress;
    }

    public URL getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(URL serverAddress) {
        this.serverAddress = serverAddress;
    }
}