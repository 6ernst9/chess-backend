package com.example.chess.model;

import java.util.Objects;

public class Pawn extends Piece{

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String getPieceName() {
        return "Pawn";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(end.getPiece()!=null && end.getPiece().getIsWhite()==this.getIsWhite())
            return false;
        else{
            int x = Math.abs(start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            boolean direction = end.getX() - start.getX() > 0;
            if( x == 1 && y == 0 && end.getPiece() ==null && direction == this.getIsWhite() && isNotCheck(board, start, end))
                return true;
            if(x==2 && y==0 ){
                if(start.getX() == 1 && end.getX() == 3 && board.getBox(2, end.getY()).getPiece()==null &&
                        isNotCheck(board, start, end) && direction == this.getIsWhite()
                        || start.getX() == 6 && end.getX() == 4 && board.getBox(5, end.getY()).getPiece()==null &&
                        isNotCheck(board, start, end) && direction == this.getIsWhite())
                return true;
            }
            return end.getPiece() != null && end.getPiece().getIsWhite() == !this.getIsWhite()
                    && x == 1 && y == 1 && isNotCheck(board, start, end) && direction == this.getIsWhite();
        }
    }
    public boolean canMoveCheckVerifier(Board board, Spot start, Spot end) {
        if(end.getPiece()!=null && end.getPiece().getIsWhite()==this.getIsWhite())
            return false;
        else{
            int x = Math.abs(start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            boolean direction = end.getX() - start.getX() > 0;
            if( x == 1 && y == 0 && end.getPiece() ==null && direction == this.getIsWhite())
                return true;
            if(x==2 && y==0 ){
                if(start.getX() == 1 && end.getX() == 3 && board.getBox(2, end.getY()).getPiece()==null && direction == this.getIsWhite()
                        || start.getX() == 6 && end.getX() == 4 && board.getBox(5, end.getY()).getPiece()==null && direction == this.getIsWhite())
                    return true;
            }
            return end.getPiece() != null && end.getPiece().getIsWhite() == !this.getIsWhite()
                    && x == 1 && y == 1 && direction == this.getIsWhite();
        }
    }
    private boolean isNotCheck( Board board, Spot start, Spot end){
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
