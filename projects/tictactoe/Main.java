package projects.tictactoe;

import projects.tictactoe.models.User;
import projects.tictactoe.services.GameService;
import projects.tictactoe.strategy.LineWinningStrategy;

public class Main {
    private final static int BOARD_SIZE = 3;
    private static GameService gameService;

    public static void main(String[] args) {
        start();
        User shubhi = new User("Shubhi");
        User prateek = new User("Prateek");
        String gameId = gameService.createGame(prateek, shubhi);
        gameService.play(gameId, 0, 0);
        gameService.play(gameId, 0, 0);
        gameService.play(gameId, 1, 1);
        gameService.play(gameId, 1, 0);
        gameService.play(gameId, 2, 0);
        gameService.play(gameId, 2, 1);
        gameService.play(gameId, 0, 2);
        gameService.play(gameId, 0, 2);
        System.out.println("Status: " + gameService.getStatus(gameId));
        User winner = gameService.getWinner(gameId);
        System.out.println("Winner: " + (winner != null ? winner.getName() : "none"));
    }

    private static void start(){
        gameService = new GameService(new LineWinningStrategy(), BOARD_SIZE);
    }
}
