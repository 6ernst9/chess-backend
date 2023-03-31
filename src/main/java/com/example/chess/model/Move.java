package com.example.chess.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity
public class Move {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int pieceCode;
    private int player;

    public Move(int startX, int startY, int endX, int endY, int pieceCode, int player) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.pieceCode = pieceCode;
        this.player = player;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getPieceCode() {
        return pieceCode;
    }

    public int getPlayer() {
        return player;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public void setPieceCode(int pieceCode) {
        this.pieceCode = pieceCode;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
