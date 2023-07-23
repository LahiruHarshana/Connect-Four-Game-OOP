package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public interface Board  {
    public  int NUM_OF_ROWS =5;
    public  int NUM_OF_COLS =6;



    public  BoardUI getBoardUI();




    int findNextAvailableSpot(int col);

    public boolean isLegalMove(int col);


    public boolean existLegalMoves();







    void updateMove(int col,Piece move);

    public Winner findWinner();

    public void updateMove(int col,int row,Piece move);





}
