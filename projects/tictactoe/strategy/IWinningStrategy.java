package projects.tictactoe.strategy;

import projects.tictactoe.enums.CellType;
import projects.tictactoe.models.Board;

public interface IWinningStrategy {
    boolean checkWin(Board board, int row, int col, CellType type);
}
