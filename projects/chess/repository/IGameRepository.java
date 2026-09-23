package projects.chess.repository;

import projects.chess.models.Game;

public interface IGameRepository {
    Game getById(String id);
    void save(Game game);
}
