package com.dopoil.gomokuclient.custom.deserializers;

import com.dopoil.gomokuclient.domain.Board;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class BoardDeserializer extends StdDeserializer<Board> {

    public BoardDeserializer() {
        this(null);
    }

    ObjectMapper mapper = new ObjectMapper();
    public BoardDeserializer(Class<Board> t) {
        super(t);
    }

    @Override
    public Board deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);
        ArrayNode boardNode = node.withArray("board");
        Integer[][] boardArray = mapper.readValue(boardNode.toString(), Integer[][].class);
        Board newBoard = new Board(boardArray.length, boardArray[0].length);
        newBoard.setBoard(boardArray);
        return newBoard;
    }
}
