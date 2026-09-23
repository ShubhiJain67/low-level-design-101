package projects.tictactoe.services;

import java.util.*;
import projects.tictactoe.enums.CellType;
import projects.tictactoe.enums.GameStatus;
import projects.tictactoe.models.*;
import projects.tictactoe.strategy.IWinningStrategy;

public class GameService {
    private final Map<String, Game> games;
    private final IWinningStrategy winningStrategy;
    private final int boardSize;

    public GameService(IWinningStrategy winningStrategy, int boardSize) {
        this.games = new HashMap<>();
        this.winningStrategy = winningStrategy;
        this.boardSize = boardSize;
    }

    public String createGame(User player1, User player2){
        if(player1 == null || player2 == null){
            throw new IllegalArgumentException("Both players are required to create a game");
        }
        if(player1 == player2){
            throw new IllegalArgumentException("player1 and player2 cannot be the same player");
        }
        Board board = new Board(this.boardSize);
        Game game = new Game(board, player1, player2);
        this.games.put(game.getId(), game);
        return game.getId();
    }

    private Game getGameOrThrow(String gameId){
        Game game = this.games.get(gameId);
        if(game == null){
            throw new IllegalArgumentException("No game found with id " + gameId);
        }
        return game;
    }

    public GameStatus getStatus(String gameId){
        return getGameOrThrow(gameId).getStatus();
    }

    public User getWinner(String gameId){
        return getGameOrThrow(gameId).getWinner();
    }

    private boolean canMove(Game game, int row, int col){
        if(!game.getBoard().isValidCellPosition(row, col)){
            return false;
        }
        if(game.getBoard().getCellType(row, col) != CellType.EMPTY){
            System.err.println("Cell is already occupied cannot play this move");
            return false;
        }
        return true;
    }

    public void play(String gameId, int row, int col){
        Game game = getGameOrThrow(gameId);
        if(game.getStatus() != GameStatus.IN_PROGRESS){
            System.err.println("Game hasn't started yet or has already ended, cannot play");
            return;
        }
        CellType type = getCellType(game);
        if(!this.canMove(game, row, col)){
            return;
        }
        game.getBoard().placePiece(row, col, type);
        System.err.println(game.getCurrentPlayer().getName() + " played " + type + " at (" + row + "," + col + ")");
        if(this.winningStrategy.checkWin(game.getBoard(), row, col, type)){
            System.err.println(game.getCurrentPlayer().getName() + " Won the game!!");
            game.setStatus(GameStatus.ENDED);
            game.setWinner(game.getCurrentPlayer());
        }
        if(game.getBoard().isBoardFull()){
            System.err.println("Game is a DRAW!!");
            game.setStatus(GameStatus.ENDED);
        }
        this.switchPlayer(game);
    }

    private CellType getCellType(Game game){
        if(game.getCurrentPlayer() == game.getPlayer1()){
            return CellType.X;
        }
        return CellType.O;
    }

    private void switchPlayer(Game game){
        if(game.getCurrentPlayer() == game.getPlayer1()){
            game.setCurrentPlayer(game.getPlayer2());
        } else {
            game.setCurrentPlayer(game.getPlayer1());
        }
    }
}
