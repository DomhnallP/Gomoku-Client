package com.dopoil.gomokuclient.service;

import com.dopoil.gomokuclient.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class PlayerSyncService {

    private Game gameState = new Game();

    public PlayerSyncService(){

    }

    public void updateGameState(Game game){
        this.gameState = game;
    }
}
