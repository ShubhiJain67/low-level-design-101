package projects.elevatorsystem;

import java.util.*;

public class Elevator {
    private final int id;
    private final Set<Integer> stops;
    private final DirectionEnum direction;
    private int currentFloor;

    public Elevator(int id) {
        this.id = id;
        this.stops = new TreeSet<>();
        this.direction = DirectionEnum.PAUSE;
        this.currentFloor = 0;
    }

    public int getId(){
        return this.id;
    }

    public boolean isElevatorFree(){
        return this.direction == DirectionEnum.PAUSE;
    }

    public boolean hasStops(){
        return !this.stops.isEmpty();
    }

    public boolean hasStop(int floor){
        return this.stops.contains(floor);
    }

    public DirectionEnum getDirection() {
        return this.direction;
    }

    public synchronized void addStop(int floor) {
        stops.add(floor);
    }

    public synchronized void removeStop(int floor) {
        stops.remove(floor);
    }

    public void halt(int floor){
        System.err.println("Stopping at floor -> " + floor);
        this.removeStop(floor);
    }

    public void setCurrentFloor(int currentFloor){
        this.currentFloor = currentFloor;
    }
    
    public int getCurrentFloor(){
        return this.currentFloor;
    }
}
