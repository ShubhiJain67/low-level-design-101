package projects.chess.models;

import java.util.UUID;
import projects.chess.enums.*;

public class Game {
    private final String Id;
    private final Board board;
    private User whitePlayer;
    private User blackPlayer;
    private User currentPlayer;
    private GameStatus status;
    private User winner;

    public Game(User whitePlayer, User blackPlayer) {
        this.Id = UUID.randomUUID().toString();
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        this.status = GameStatus.IN_PROGRESS;
    }

    public String getId(){
        return this.Id;
    }
    
    public Board getBoard(){
        return this.board;
    }
    
    public User getWhitePlayer(){
        return this.whitePlayer;
    }

    public User getBlackPlayer(){
        return this.blackPlayer;
    }

    public void setWhitePlayer(User player){
        this.whitePlayer = player;
    }

    public void setBlackPlayer(User player){
        this.blackPlayer = player;
    }

    public User getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setCurrentPlayer(User player){
        this.currentPlayer = player;
    }

    public GameStatus getGameStatus(){
        return this.status;
    }

    public User getWinner(){
        return this.winner;
    }

    public void endGame(User winner){
        this.status = GameStatus.ENDED;
        this.winner = winner;
    }
}
