package com.example.chess.model;

public class Board {
    private Spot[][] boxes= new Spot[8][8];
    public Spot getBox(int x, int y){
        return boxes[x][y];
    }
}
