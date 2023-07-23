package lk.ijse.dep.service;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {
    boolean run = true;
    boolean isLegal;
    Winner winner;
    Random rand = new Random();
    int num;

    public AiPlayer(Board newboard) {
        super(newboard);
    }
    @Override
    public void movePiece(int indexOf) {
        num= bestMove();

            board.updateMove(num,Piece.GREEN);
            board.getBoardUI().update(num,isLegal);
            winner= board.findWinner();
            if(winner.getWinningPiece()!=Piece.EMPTY){
                board.getBoardUI().notifyWinner(winner);
            }else {
                if (board.existLegalMoves()==false){
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }
    }
    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {//
            return -1;
        } else if (board.existLegalMoves() && depth <= 5) {
            int heuristicVal;
            if (maximizingPlayer) {
                int maxEvA = -1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = minimax(depth +1, false);
                        maxEvA = max(maxEvA,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return maxEvA;
                        }
                    }
                }

            } else {

                int minEva= 1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = minimax(depth + 1, true);
                        minEva= min(minEva,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return minEva;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    private int bestMove() {
        boolean isUserWinning = false;
        int winningCol = 0;
        for (int i = 0; i < 6; ++i) {
            if (board.isLegalMove(i)) {
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                board.updateMove(i, row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return i;
                }else if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    winningCol = i;
                }
            }
        }
        System.out.println();

        if (isUserWinning && board.isLegalMove(winningCol)) {
            return winningCol;
        } else {
            int j;
            do {
                j = (int) ((Math.random() * ((5 - 0) + 1)) + 0);
            } while (!board.isLegalMove(j));
            return j;
        }
    }
}
