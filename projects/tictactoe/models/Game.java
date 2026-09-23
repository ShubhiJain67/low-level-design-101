package projects.tictactoe.models;

import java.util.UUID;
import projects.tictactoe.enums.GameStatus;

public class Game {
    private final String id;
    private final Board board;
    private final User player1;
    private final User player2;
    private User currentPlayer;
    private GameStatus status;
    private User winner;

    public Game(Board board, User player1, User player2) {
        this.id = UUID.randomUUID().toString();
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.status = GameStatus.IN_PROGRESS;
        this.winner = null;
    }

    public String getId(){
        return this.id;
    }

    public Board getBoard(){
        return this.board;
    }

    public User getPlayer1(){
        return this.player1;
    }

    public User getPlayer2(){
        return this.player2;
    }

    public User getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setCurrentPlayer(User currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public GameStatus getStatus(){
        return this.status;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }

    public User getWinner(){
        return this.winner;
    }

    public void setWinner(User winner){
        this.winner = winner;
    }
}
