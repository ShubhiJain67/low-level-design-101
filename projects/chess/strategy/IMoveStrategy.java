package projects.chess.strategy;

import projects.chess.enums.Team;
import projects.chess.models.*;

public interface IMoveStrategy {
    boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol, Team team);
}
