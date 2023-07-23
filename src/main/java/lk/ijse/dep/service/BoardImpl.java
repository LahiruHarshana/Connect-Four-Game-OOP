package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {

    Piece[][] piece;
    BoardUI boardUI;
    Piece winningPiece;
    int col1;
    int row1;
    int col2;
    int row2;
    BoardController boardController;

    public BoardImpl(BoardController boardController) {

        piece = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        this.boardController=boardController;

        boardUI=boardController;

        for (int i = 0; i < NUM_OF_COLS; i++){
            for (int j=0; j<NUM_OF_ROWS; j++){
                piece[i][j]=Piece.EMPTY;
            }
        }
    }



    @Override
    public BoardUI getBoardUI() {
        return  boardController;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for(int i = 0; i < NUM_OF_ROWS; i++) {
            if(piece[col][i] == Piece.EMPTY){
                return(i);
            }
        }
        return(-1);
    }
    @Override
    public boolean isLegalMove(int col) {
        int place=findNextAvailableSpot(col);
        if (place==-1){
            return false;
        }
        return (true);
    }

    @Override
    public boolean existLegalMoves() {
        for (int i=0; i< NUM_OF_COLS; i++){
            for(int j=0; j<NUM_OF_ROWS; j++){
                if(piece[i][j]==Piece.EMPTY){
                    return true;
                }
            }
        }
         return false;

    }
    @Override
    public void updateMove(int col, Piece move) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (piece[col][i]==Piece.EMPTY){
                piece[col][i]=move;
                break;
            }
            
        }

    }
    @Override
    public Winner findWinner() {

        for (int i = 0; i < NUM_OF_COLS; i++) {
            winningPiece=Piece.EMPTY;

            if(piece[i][0]==piece[i][1] && piece[i][1]==piece[i][2] && piece[i][2]==piece[i][3]){
                if(piece[i][0]!=Piece.EMPTY) {
                    winningPiece = piece[i][0];
                    col1 = i;
                    col2 = i;
                    row1 = 0;
                    row2 = 3;
                }
                break;
            }
            else if(piece[i][1]==piece[i][2] && piece[i][2]==piece[i][3] && piece[i][3]==piece[i][4]){
                if (piece[i][1]!=Piece.EMPTY) {
                    winningPiece = piece[i][1];
                    col1 = i;
                    col2 = i;
                    row1 = 1;
                    row2 = 4;
                }
                break;
            }
        }

        for(int i =0; i< NUM_OF_ROWS; i++){

            if(piece[2][i]==piece[3][i]&&piece[3][i]==piece[4][i]&&piece[4][i]==piece[5][i]){
                if (piece[2][i]!=Piece.EMPTY) {
                    winningPiece = piece[2][i];
                    col1 = 2;
                    col2 = 5;
                    row1 = i;
                    row2 = i;
                }

                break;
            }
            else if(piece[0][i]==piece[1][i]&&piece[1][i]==piece[2][i]&&piece[2][i]==piece[3][i]){
                if(piece[0][i]!=Piece.EMPTY) {
                    winningPiece = piece[0][i];
                    col1 = 0;
                    col2 = 3;
                    row1 = i;
                    row2 = i;
                }
                break;
            }
            else if(piece[1][i]==piece[2][i] && piece[2][i]==piece[3][i] && piece[3][i]==piece[4][i]){
                if(piece[1][i]!=Piece.EMPTY) {
                    winningPiece = piece[1][i];
                    col1 = 1;
                    col2 = 4;
                    row1 = i;
                    row2 = i;
                }
                break;
            }
        }

        if(winningPiece==Piece.EMPTY){
            return new Winner(Piece.EMPTY);
        }
        else {
            return new Winner(winningPiece,col1,row1,col2,row2);
        }
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        piece[col][row]=move;


    }
}