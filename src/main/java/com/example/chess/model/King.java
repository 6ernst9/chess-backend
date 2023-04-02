package com.example.chess.model;

public class King extends Piece{
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String getPieceName() {
        return "King";
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
        if( end.getPiece().getIsWhite() == this.getIsWhite())
            return false;
        else{
            int x = Math.abs( start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            return x + y == 1 && isNotCheck(board, start, end) || x == y && y== 1 && isNotCheck(board, start, end);
        }
    }

    @Override
    boolean canMoveCheckVerifier(Board board, Spot start, Spot end) {
        if( end.getPiece().getIsWhite() == this.getIsWhite())
            return false;
        else{
            int x = Math.abs( start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            return x + y == 1 || x == y && y== 1;
        }
    }
    private boolean isNotCheck(Board board, Spot start, Spot end){
        Piece piece = board.getBox(start.getX(), start.getY()).getPiece();
        board.getBox(start.getX(), start.getY()).setPiece( null);
        Piece intermediatePiece = board.getBox(end.getX(), end.getY()).getPiece();
        board.getBox(end.getX(), end.getY()).setPiece( piece);

        for( int i=0; i<8;i++){
            for( int j=0; j<8; j++){
                if(board.getBox(i, j).getPiece() !=null && board.getBox(i, j).getPiece().getIsWhite() != end.getPiece().getIsWhite()
                        && board.getBox(i, j).getPiece().canMoveCheckVerifier(board, new Spot(i,j), end)){
                    board.getBox(start.getX(), start.getY()).setPiece( piece);
                    board.getBox(end.getX(), end.getY()).setPiece( intermediatePiece );
                    return false;
                }
            }
        }
        board.getBox(start.getX(), start.getY()).setPiece(piece);
        board.getBox(end.getX(), end.getY()).setPiece(intermediatePiece);
        return true;
    }
}
