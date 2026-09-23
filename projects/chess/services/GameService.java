package projects.chess.services;

import projects.chess.enums.*;
import projects.chess.models.*;
import projects.chess.repository.IGameRepository;
import projects.chess.strategy.IMoveStrategy;
import projects.chess.strategy.MoveStrategyFactory;

public class GameService {
    private final IGameRepository repository;

    public GameService(IGameRepository repo) {
        this.repository = repo;
    }

    public String createGame(User whitePlayer, User blackPlayer){
        Game game = new Game(whitePlayer, blackPlayer);
        repository.save(game);
        return game.getId();
    }

    public Game getGame(String gameId){
        Game game = this.repository.getById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("No game found with id " + gameId);
        }
        return game;
    }

    public void move(String gameId, int fromRow, int fromCol, int toRow, int toCol){
        Game game = getGame(gameId);
        if (game.getGameStatus() != GameStatus.IN_PROGRESS) {
            System.err.println("Game is not in progress, cannot move");
            return;
        }

        Board board = game.getBoard();
        ChessPiece piece = board.getPieceAt(fromRow, fromCol);
        Team movingTeam = board.getTeamAt(fromRow, fromCol);
        Team currentTeam = game.getCurrentPlayer() == game.getWhitePlayer() ? Team.WHITE : Team.BLACK;

        if (piece == ChessPiece.NONE) {
            System.err.println("No piece at (" + fromRow + "," + fromCol + ")");
            return;
        }
        if (movingTeam != currentTeam) {
            System.err.println(game.getCurrentPlayer().getName() + " cannot move the opponent's piece");
            return;
        }

        IMoveStrategy strategy = MoveStrategyFactory.getStrategy(piece);
        if (!strategy.isValidMove(board, fromRow, fromCol, toRow, toCol, movingTeam)) {
            System.err.println("Invalid move for " + piece + " from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")");
            return;
        }

        board.movePiece(fromRow, fromCol, toRow, toCol);
        this.switchPlayer(game);
    }

    private void switchPlayer(Game game){
        if (game.getCurrentPlayer() == game.getWhitePlayer()) {
            game.setCurrentPlayer(game.getBlackPlayer());
        } else {
            game.setCurrentPlayer(game.getWhitePlayer());
        }
    }
}
