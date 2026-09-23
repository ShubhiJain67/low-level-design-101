package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public class KnightMoveStrategy implements IMoveStrategy {
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        int colDiff = Math.abs(toCol - fromCol);
        int rowDiff = Math.abs(toRow - fromRow);
        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (!isLShape){
            return false;
        }
        return board.getTeamAt(toRow, toCol) != team;
    }
}
