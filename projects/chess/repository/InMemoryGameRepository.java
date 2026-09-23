package projects.chess.repository;

import java.util.*;
import projects.chess.models.Game;

public class InMemoryGameRepository implements IGameRepository{
    private final Map<String, Game> store;

    public InMemoryGameRepository() {
        this.store = new HashMap<>();
    }

    @Override
    public Game getById(String id){
        return this.store.get(id);
    }

    @Override
    public void save(Game game){
        this.store.put(game.getId(), game);
    }
}
