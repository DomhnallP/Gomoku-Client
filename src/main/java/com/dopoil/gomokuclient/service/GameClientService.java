package com.dopoil.gomokuclient.service;

import com.dopoil.gomokuclient.domain.Board;
import com.dopoil.gomokuclient.domain.Game;
import com.dopoil.gomokuclient.domain.Move;
import com.dopoil.gomokuclient.domain.Player;
import com.dopoil.gomokuclient.properties.GomokuClientProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class GameClientService {

    private final GomokuClientProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Scanner sc = new Scanner(System.in);

    public Game connectToGame(Player player) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        byte[] bytes = objectMapper.writeValueAsBytes(player);
        HttpEntity entity = EntityBuilder.create().setBinary(bytes).build();
        HttpPost httpPost = new HttpPost(properties.getServerAddress() + "/game");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(httpPost);
        String json = EntityUtils.toString(response.getEntity());
        client.close();
        Game newGame = objectMapper.readValue(json.getBytes(), Game.class);
        return newGame;
    }

    public Game makeMove(Game currentGame, Player player) throws IOException {

        boolean legalMove = false;
        int selectedColumn = 0;
        while (!legalMove){
            System.out.println("Please select a column between 1-" + currentGame.getBoard().getWidth());
            selectedColumn = sc.nextInt() - 1;
            legalMove = isMoveLegal(currentGame.getBoard(), selectedColumn);
        }
        Move newMove = new Move();
        newMove.setGameId(currentGame.getGameId());
        newMove.setPlayer(player);
        newMove.setType(player.getToken());
        newMove.setColumnIndex(selectedColumn);


        CloseableHttpClient client = HttpClients.createDefault();
        byte[] bytes = objectMapper.writeValueAsBytes(newMove);
        HttpEntity entity = EntityBuilder.create().setBinary(bytes).build();
        HttpPost httpPost = new HttpPost(properties.getServerAddress() + "/move");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(httpPost);
        String json = EntityUtils.toString(response.getEntity());
        client.close();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Game newGame = objectMapper.readValue(json.getBytes(), Game.class);

        return newGame;
    }

    public boolean isMoveLegal(Board board, int columnIndex) {

        return columnIndex < board.getWidth() && board.getBoard()[0][columnIndex] == 0 ? true : false;

    }


    public void displayBoard(Board board) {

        System.out.println(Arrays.deepToString(board.getBoard())
                .replace("],", "]\n")
                .replace(",", "][")
                .replace("]]", "]")
                .replace("[[", " [")
                .replace("0", " ")
                .replace("1", "X")
                .replace("2", "O")
                .replace("  ", " ")
                .replace(" X", "X")
                .replace(" O", "O"));
    }

}
