package projects.chess;

import projects.chess.models.*;
import projects.chess.repository.*;
import projects.chess.services.GameService;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService(new InMemoryGameRepository());

        User prateek = new User("Prateek");
        User shubhi = new User("Shubhi");

        String gameId = gameService.createGame(prateek, shubhi);
        System.out.println("Game started: " + prateek.getName() + " (White) vs " + shubhi.getName() + " (Black)");
        System.out.println("Game id: " + gameId);
        System.out.println();

        System.out.println("White: e2 -> e4");
        gameService.move(gameId, 1, 4, 3, 4);

        System.out.println("Black: e7 -> e5");
        gameService.move(gameId, 6, 4, 4, 4);

        System.out.println("White: Ng1 -> f3");
        gameService.move(gameId, 0, 6, 2, 5);

        System.out.println("Black: Nb8 -> c6");
        gameService.move(gameId, 7, 1, 5, 2);

        System.out.println("White: Bf1 -> c4");
        gameService.move(gameId, 0, 5, 3, 2);

        System.out.println("Black (illegal): pawn e5 -> e6 (backward)");
        gameService.move(gameId, 4, 4, 5, 4);

        System.out.println("White (illegal): tries to move out of turn");
        gameService.move(gameId, 2, 5, 4, 4);

        System.out.println("Black: Bf8 -> c5");
        gameService.move(gameId, 7, 5, 4, 2);

        System.out.println("White: Nf3 x e5 (captures pawn)");
        gameService.move(gameId, 2, 5, 4, 4);

        System.out.println("Black: Nc6 x e5 (recaptures knight)");
        gameService.move(gameId, 5, 2, 4, 4);
    }
}
