package com.dopoil.gomokuclient.domain;

import lombok.Data;
import java.net.URL;
import java.util.UUID;


@Data
public class Player {

    private UUID id;
    private String user;
    private URL networkId;
    private int token;
}
