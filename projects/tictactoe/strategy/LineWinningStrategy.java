package projects.tictactoe.strategy;

import projects.tictactoe.enums.CellType;
import projects.tictactoe.models.Board;


public class LineWinningStrategy implements IWinningStrategy {
    @Override
    public boolean checkWin(Board board, int currRow, int currCol, CellType currType){
        int boardSize = board.getSize();

        // Check for row
        int validCount = 0;
        for (int col = 0; col < boardSize; col++) {
            if(board.getCellType(currRow, col) != currType){
                break;
            } else {
                validCount += 1;
            }
            if(validCount == boardSize){
                return true;
            }
        }

        // Check for col
        validCount = 0;
        for (int row = 0; row < boardSize; row++) {
            if(board.getCellType(row, currCol) != currType){
                break;
            } else {
                validCount += 1;
            }
            if(validCount == boardSize){
                return true;
            }
        }

        // Check for diagonals left diagonal
        if(currCol == currRow){
            validCount = 0;
            for (int col = 0; col < boardSize; col++) {
                if(board.getCellType(col, col) != currType){
                    break;
                } else {
                    validCount += 1;
                }
            }
            if(validCount == boardSize){
                return true;
            }
        }

        // Check for diagonals right diagonal
        if(currCol + currRow == boardSize - 1){
            validCount = 0;
            for (int col = 0; col < boardSize; col++) {
                if(board.getCellType(col, boardSize-col-1) != currType){
                    break;
                } else {
                    validCount += 1;
                }
            }
        }
        return validCount == boardSize;
    }
}
