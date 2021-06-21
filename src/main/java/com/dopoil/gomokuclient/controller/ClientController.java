package com.dopoil.gomokuclient.controller;

import com.dopoil.gomokuclient.domain.Game;
import com.dopoil.gomokuclient.service.PlayerSyncService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class ClientController {

    private final PlayerSyncService playerSyncService;

    @PostMapping("/sync")
    public ResponseEntity syncPlayers(@RequestBody Game game ){

        try {
            playerSyncService.updateGameState(game);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
