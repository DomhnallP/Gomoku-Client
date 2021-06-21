package com.dopoil.gomokuclient.domain;

import com.dopoil.gomokuclient.custom.BoardObject;
import com.dopoil.gomokuclient.custom.deserializers.BoardDeserializer;
import com.dopoil.gomokuclient.custom.serializers.BoardSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@JsonSerialize(using = BoardSerializer.class)
@JsonDeserialize(using = BoardDeserializer.class)
public class Board {

    private UUID id;
    private int width;
    private int height;
    private BoardObject board;
    private int winCondition;

    public Board() {
    }

    public Board(final int height, final int width, final int winCondition){
        this.height = height;
        this.width = width;
        this.winCondition = winCondition;
        this.board = new BoardObject(new Integer[height][width]);
        for(final Integer[] row : board.getData()){
            fill(row, 0);
        }
    }

    public Board(final int height, final int width){
        this.height = height;
        this.width = width;
        this.board = new BoardObject(new Integer[height][width]);
        for(final Integer[] row : board.getData()){
            fill(row, 0);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Integer[][] getBoard() {
        return board.getData();
    }

    public void setBoard(Integer[][] board) {
        this.board.setData(board);
    }

    public void applyMove(Move move) {

        for(int i = this.height-1 ; i>= 0 ; i--) {
            if (this.board.getData()[i][move.getColumnIndex()] == 0){
                this.board.getData()[i][move.getColumnIndex()] = move.getType();
                break;
            }
        }


    }

    public Optional<Integer> getWinner() {
        Optional<Integer> winner = empty();
        for (final Integer token : asList(1, 2)) {
            if (hasHorizontalWin(token) || hasVerticalWin(token)) {
                winner = of(token);
                break;
            }
        }
        return winner;
    }

    public boolean isFull() {
        return stream(board.getData()).flatMap(x -> stream(x)).noneMatch(e -> 0==e);
    }

    public Optional<Integer> checkForWinner(){
        Optional<Integer> winner = empty();
        for(final int token : asList(1,2)){
            if(hasHorizontalWin(token)||hasVerticalWin(token)){
                winner = of(token);
                break;
            }
        }

        return winner;

    }

    private boolean hasHorizontalWin(final int winnerType) {
        int actualCount = 0;
        for (final Integer[] row : board.getData()) {
            for (final int column : row) {
                actualCount = increaseOrResetCount(column, winnerType, actualCount);
                if (actualCount == winCondition) {
                    return true;
                }
            }
            actualCount = 0;
        }
        return false;
    }

    private boolean hasVerticalWin(final int winnerType) {
        int actualCount = 0;
        for (int column = 0; column < board.getData()[0].length; column++) {
            for (int row = 0; row < board.getData().length; row++) {

                actualCount = increaseOrResetCount(board.getData()[row][column], winnerType, actualCount);
                if (actualCount == winCondition) {
                    return true;
                }
            }
            actualCount = 0;
        }
        return false;
    }

    private int increaseOrResetCount(final int actualField, final int winnerType, final int count) {
        return actualField == winnerType ? count + 1 : 0;
    }
}
