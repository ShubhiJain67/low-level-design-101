package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public class QueenMoveStrategy implements IMoveStrategy {
    private final IMoveStrategy rookMove = new RookMoveStrategy();
    private final IMoveStrategy bishopMove = new BishopMoveStrategy();
    
    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team){
        return rookMove.isValidMove(board, fromRow, fromCol, toRow, toCol, team)
            || bishopMove.isValidMove(board, fromRow, fromCol, toRow, toCol, team);
    }
}
