package com.example.chess.model;

import java.util.Objects;

public class Queen extends Piece{
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String getPieceName() {
        return "Queen";
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
       if(end.getPiece()!=null && end.getPiece().getIsWhite()==getIsWhite())
           return false;
       else{
           int x = Math.abs(start.getX() - end.getX());
           int y = Math.abs(start.getY() - end.getY());
           if(x*y==0)
               return isClearVertically(board, start, end) && isClearHorizontally(board, start, end) && isNotCheck(board, start, end);
           if(x==y)
               return isClearDiagonally(board, start, end) &&  isNotCheck(board, start, end);
       }
       return false;
    }

    @Override
    boolean canMoveCheckVerifier(Board board, Spot start, Spot end) {
        if(end.getPiece()!=null && end.getPiece().getIsWhite()==getIsWhite())
            return false;
        else{
            int x = Math.abs(start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            if(x*y==0)
                return isClearVertically(board, start, end) && isClearHorizontally(board, start, end);
            if(x==y)
                return isClearDiagonally(board, start, end);
        }
        return false;
    }

    private boolean isClearDiagonally(Board board, Spot start, Spot end){
        int gap = Math.abs(end.getY() - start.getY())-1;
        if(gap==0) return true;
        for(int i=1;i<=gap;i++){
            int nextY = end.getY() > start.getY() ? start.getY()+i : start.getY() - i;
            int nextX = end.getX() > start.getX() ? start.getX()+i : start.getX() - i;
            if(board.getBox(nextX, nextY).getPiece()!=null){
                return false;
            }
        }
        return true;
    }

    private boolean isClearHorizontally(Board board, Spot start, Spot end){
        int gap = Math.abs(end.getY() - start.getY())-1;
        if(gap==0)
            return true;
        for(int i=1;i<=gap;i++){
            int nextBox = end.getY() > start.getY() ? start.getY()+i : start.getY() - i;
            if(board.getBox(start.getX(), nextBox).getPiece()!=null){
                return false;
            }
        }
        return true;
    }

    private boolean isClearVertically(Board board, Spot start, Spot end){
        int gap = Math.abs(end.getX() - start.getX())-1;
        if(gap==0)
            return true;
        for(int i=1;i<=gap;i++){
            int nextBox = end.getX() > start.getX() ? start.getX()+i : start.getX() - i;
            if(board.getBox(nextBox, start.getY()).getPiece()!=null){
                return false;
            }
        }
        return true;
    }
    private boolean isNotCheck(Board board, Spot start, Spot end){
        Piece piece = board.getBox(start.getX(), start.getY()).getPiece();
        board.getBox(start.getX(), start.getY()).setPiece( null);
        Piece intermediatePiece = board.getBox(end.getX(), end.getY()).getPiece();
        board.getBox(end.getX(), end.getY()).setPiece( piece);

        Spot kingSpot = null;
        for( int i=0; i<8;i++){
            for( int j=0; j<8; j++){
                if(Objects.equals(board.getBox(i, j).getPiece().getPieceName(), "King") && board.getBox(i, j).getPiece().getIsWhite() == piece.getIsWhite()){
                    kingSpot = board.getBox(i, j);
                }
            }
        }

        for( int i=0; i<8;i++){
            for( int j=0; j<8; j++){
                if(board.getBox(i, j).getPiece() != null && board.getBox(i, j).getPiece().canMoveCheckVerifier(board, board.getBox(i, j), kingSpot)) {
                    assert kingSpot != null;
                    if (board.getBox(i, j).getPiece().getIsWhite() != kingSpot.getPiece().getIsWhite()) {
                        board.getBox(start.getX(), start.getY()).setPiece(piece);
                        board.getBox(end.getX(), end.getY()).setPiece(intermediatePiece);
                        return false;
                    }
                }
            }
        }
        board.getBox(start.getX(), start.getY()).setPiece(piece);
        board.getBox(end.getX(), end.getY()).setPiece(intermediatePiece);
        return true;
    }

}
