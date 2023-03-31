package com.example.chess.model;

abstract public class Piece {
    private boolean isKilled;
    private final boolean isWhite;
    private String pieceName;
    public Piece(boolean isWhite){
        this.isWhite = isWhite;
        isKilled = false;
    }
    public boolean getIsWhite(){
        return this.isWhite;
    }
    public void setKilled(){
        isKilled = true;
    }
    abstract public String getPieceName();
    public boolean getIsKilled(){
        return this.isKilled;
    }
    abstract boolean canMove(Board board, Spot start, Spot end);
    abstract boolean canMoveCheckVerifier(Board board, Spot start, Spot end);

}
