package projects.movieticketbooking.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Theater {
    private final String id;
    private final List<Screen> screens;

    public Theater() {
        this.id = UUID.randomUUID().toString();
        this.screens = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public boolean hasScreen(Screen screen){
        if(screen == null){
            throw new IllegalArgumentException("found an empty screen");
        }
        return this.screens.contains(screen);
    }

    public List<Screen> getScreens() {
        return this.screens;
    }

    public void addScreen(Screen screen){
        if(screen == null){
            throw new IllegalArgumentException("found an empty screen");
        }
        if(this.hasScreen(screen)){
            throw new IllegalStateException("Theater already has this screen");
        }
        this.screens.add(screen);
    }

    public void removeScreen(Screen screen){
        if(screen == null){
            throw new IllegalArgumentException("found an empty screen");
        }
        if(!this.hasScreen(screen)){
            throw new IllegalStateException("Theater doesnot have this screen");
        }
        this.screens.remove(screen);
    }
}
