package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public class BishopMoveStrategy implements IMoveStrategy {
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        if (rowDiff == 0 || rowDiff != colDiff){
            return false;
        }
        if (!board.isPathClear(fromRow, fromCol, toRow, toCol)){
            return false;
        }
        return board.getTeamAt(toRow, toCol) != team;
    }
}
