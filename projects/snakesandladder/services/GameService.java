package projects.snakesandladder.services;

import java.util.*;
import projects.snakesandladder.models.*;

public class GameService {
    private final Board board;
    private final Map<User, Cell> players;
    private final Dice dice;
    private final Map<User, User> nextPlayer;
    private User currentPlayer;
    private int movesCount;
    private final int MAXIMUM_MOVE_COUNT;

    public GameService(Board board, Dice dice, int maximumMoveCount) {
        this.board = board;
        this.players = new HashMap<>();
        this.dice = dice;
        this.nextPlayer = new HashMap<>();
        this.movesCount = 0;
        this.MAXIMUM_MOVE_COUNT = maximumMoveCount;
    }

    public void addPlayers(List<User> players){
        if(players == null){
            throw new IllegalArgumentException("Game should have a minimum of 2 players found None");
        }
        if(players.size() < 2){
            throw new IllegalArgumentException("Game should have a minimum of 2 players found " + players.size());
        }
        for (int i = 0; i < players.size()-1; i++) {
            User player1 = players.get(i);
            User player2 = players.get(i+1);
            nextPlayer.put(player1, player2);
            this.players.put(player1, this.board.getStartCell());
        }
        this.players.put(players.getLast(), this.board.getStartCell());
        nextPlayer.put(players.getLast(), players.getFirst());
        this.currentPlayer = players.getFirst();
    }

    public void startGame(){
        while(this.movesCount < this.MAXIMUM_MOVE_COUNT){
            playMove();
            if(this.board.isEndCell(this.players.get(this.currentPlayer))){
                System.err.println(this.currentPlayer.getName() + " won the game!");
                return;
            }
            this.currentPlayer = this.nextPlayer.get(this.currentPlayer);
            this.movesCount += 1;
        }
    }

    private void playMove(){
        int moveCount = this.dice.play();
        Cell currCell = this.players.get(this.currentPlayer);
        if(currCell.getId() + moveCount > board.getSize()) {
            System.err.println(this.currentPlayer.getName() + " cannot play this move with " + moveCount);
            return;
        }
        Cell newCell = this.board.getCell(currCell.getId() + moveCount);
        System.err.println(this.currentPlayer.getName() + " is moving " + moveCount + " moves and now at " + newCell.getId());
        while(newCell.getNextCell() != null){
            System.err.println("Moving from " + newCell.getId() + " to " + newCell.getNextCell().getId());
            newCell = newCell.getNextCell();
        }
        players.put(this.currentPlayer, newCell);
    }
    
}
