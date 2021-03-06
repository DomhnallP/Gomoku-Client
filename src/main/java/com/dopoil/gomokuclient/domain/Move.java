package com.dopoil.gomokuclient.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Move {

    public UUID gameId;
    private int type;
    private Integer columnIndex;
    private Player player;
}
