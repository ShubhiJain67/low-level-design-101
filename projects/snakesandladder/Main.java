package projects.snakesandladder;

import java.util.ArrayList;
import java.util.List;
import projects.snakesandladder.models.*;
import projects.snakesandladder.services.GameService;

public class Main {
    private final static int DICE_OPTION_LIMIT = 6;
    private final static int BOARD_SIZE = 100;
    private final static int MAXIMUM_MOVE_COUNT = 1000;
    private static GameService gameService;

    public static void main(String[] args) {
        start();
        User shubhi = new User("Shubhi");
        User prateek = new User("Prateek");
        List<User> players = new ArrayList<>(List.of(shubhi, prateek));
        gameService.addPlayers(players);
        gameService.startGame();
    }

    private static void start(){
        Board board = new Board(BOARD_SIZE);
        board.addSnake(10, 5);
        board.addSnake(15, 2);
        board.addSnake(99, 20);
        board.addLadder(51, 72);
        board.addLadder(62, 89);
        board.addLadder(5, 98);
        Dice dice = new Dice(DICE_OPTION_LIMIT);
        gameService = new GameService(board, dice, MAXIMUM_MOVE_COUNT);
    }
}
