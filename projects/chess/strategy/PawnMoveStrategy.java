package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public class PawnMoveStrategy implements IMoveStrategy {
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        int colDiff = Math.abs(toCol - fromCol);
        int rowDiff = toRow - fromRow;
        int direction = team == Team.WHITE ? 1 : -1;

        if(colDiff == 0 && rowDiff == 2 * direction && (fromRow == 1 || fromRow == 6)){
            int midRow = fromRow + direction;
            return board.getTeamAt(midRow, fromCol) == Team.NONE && board.getTeamAt(toRow, toCol) == Team.NONE;
        }
        else if(colDiff == 0 && rowDiff == direction){
            return board.getTeamAt(toRow, toCol) == Team.NONE;
        }
        else if(colDiff == 1 && rowDiff == direction){
            return board.getTeamAt(toRow, toCol) != team && board.getTeamAt(toRow, toCol) != Team.NONE;
        }
        return false;
    }
}
