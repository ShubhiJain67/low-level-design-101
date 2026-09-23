package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;


public class KingMoveStrategy implements IMoveStrategy {
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        boolean stayedPut = rowDiff == 0 && colDiff == 0;
        boolean isOneStep = rowDiff <= 1 && colDiff <= 1;
        if (!isOneStep || stayedPut){
            return false;
        }
        return board.getTeamAt(toRow, toCol) != team;
    }
}
