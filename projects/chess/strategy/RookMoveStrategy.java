package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public class RookMoveStrategy implements IMoveStrategy {
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        boolean sameRow = fromRow == toRow;
        boolean sameCol = fromCol == toCol;
        boolean stayedPut = sameRow && sameCol;
        if ((!sameRow && !sameCol) || stayedPut){
            return false;
        }
        if (!board.isPathClear(fromRow, fromCol, toRow, toCol)){
            return false;
        }
        return board.getTeamAt(toRow, toCol) != team;
    }
}
