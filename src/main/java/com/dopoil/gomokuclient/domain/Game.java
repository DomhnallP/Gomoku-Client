package com.dopoil.gomokuclient.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Game {


    private UUID gameId;
    private Player player1;
    private Player player2;
    private int activeToken;
    private GameStatus gameStatus;
    private Integer winner;
    private Board board;

}
