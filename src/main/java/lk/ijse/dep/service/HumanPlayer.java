package lk.ijse.dep.service;


import lk.ijse.dep.controller.BoardController;

public class HumanPlayer extends Player {
    int col1;

    boolean isTrue;

    Winner winner;

    Board newBoard;

    public HumanPlayer(Board newBoard){
        super(newBoard);


    }


    @Override
    public void movePiece(int col1) {
        isTrue = board.isLegalMove(col1);
        if(isTrue) {
            board.updateMove(col1, Piece.BLUE);
            board.getBoardUI().update(col1, isTrue);

            winner = board.findWinner();

            if (winner.getWinningPiece() != Piece.EMPTY) {
                board.getBoardUI().notifyWinner(winner);
            } else {
                if (board.existLegalMoves() != true) {
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }
        }



    }
}
