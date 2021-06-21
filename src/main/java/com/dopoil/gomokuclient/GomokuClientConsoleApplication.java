package com.dopoil.gomokuclient;

import com.dopoil.gomokuclient.domain.Game;
import com.dopoil.gomokuclient.domain.GameStatus;
import com.dopoil.gomokuclient.domain.Player;
import com.dopoil.gomokuclient.properties.GomokuClientProperties;
import com.dopoil.gomokuclient.service.PlayerSyncService;
import com.dopoil.gomokuclient.service.GameClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class GomokuClientConsoleApplication implements CommandLineRunner {

    private final GomokuClientProperties properties;
    private final PlayerSyncService playerSyncService;
    private final GameClientService gameClientService;

    private static Logger LOG = LoggerFactory
            .getLogger(GomokuClientConsoleApplication.class);

    public GomokuClientConsoleApplication(GomokuClientProperties properties, PlayerSyncService playerSyncService, GameClientService gameClientService) {
        this.properties = properties;
        this.playerSyncService = playerSyncService;
        this.gameClientService = gameClientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GomokuClientConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {

        boolean myTurn = true;
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        System.out.println("Welcome To Gomoku, Please type your name and press enter");
        player.setUser(sc.nextLine());
        player.setNetworkId(properties.getNetworkAddress());
        System.out.println("Thank you " + player.getUser() + ", we will now connect you to a game");
        Game currentGame = gameClientService.connectToGame(player);
        System.out.println("connected to game " + currentGame.getGameId());
        if (currentGame.getGameStatus() == GameStatus.NEW) {
            System.out.println("You Are Player 1");
            System.out.println("Waiting for Player 2 to connect");
            player = currentGame.getPlayer1();
            myTurn = true;
            while (currentGame.getGameStatus() == GameStatus.NEW) {
                if(playerSyncService.getGameState().getPlayer2()!=null){
                    currentGame.setGameStatus(GameStatus.IN_PROGRESS);
                }
                Thread.sleep(1000);
            }
            System.out.println("Player 2 has connected, Starting Game");
        } else if (currentGame.getGameStatus() == GameStatus.IN_PROGRESS) {
            System.out.println("You are Player 2");
            System.out.println("Starting Game");
            player = currentGame.getPlayer2();
            myTurn = false;
        }
        while (currentGame.getWinner() == 0) {
            clearScreen();
            gameClientService.displayBoard(currentGame.getBoard());
            if (player.getToken() != currentGame.getActiveToken()) {
                System.out.println("waiting on other player to complete move");
                while (player.getToken() != playerSyncService.getGameState().getActiveToken()) {
                    Thread.sleep(500);
                }
                currentGame = playerSyncService.getGameState();

            }else if (player.getToken() == currentGame.getActiveToken()) {
                System.out.println("Your Turn:");
                currentGame = gameClientService.makeMove(currentGame, player);
                playerSyncService.setGameState(currentGame);
            }

        }
        currentGame.setGameStatus(GameStatus.FINISHED);
        if (currentGame.getWinner() == player.getToken()) {
            System.out.println("Congratulations! You've won this match!");
        } else if (currentGame.getWinner() != player.getToken()) {
            System.out.println("Unlucky, You lost this game, better luck next time! ");
        }
    }

    public static void clearScreen() {
        System.out.flush();
    }
}
