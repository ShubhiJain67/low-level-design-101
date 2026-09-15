package projects.elevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final List<Integer> floors;
    private final List<Elevator> elevators;
    private final ElevatorController controller;

    public Building() {
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
        this.controller = new ElevatorController();
        this.controller.setBuilding(this);
    }

    public void addFloor(int floor){
        this.floors.add(floor);
    }

    public void addElevator(Elevator elevator){
        this.elevators.add(elevator);
    }

    public List<Integer> getFloors(){
        return this.floors;
    }

    public List<Elevator> getElevators(){
        return this.elevators;
    }

    public Elevator getElevator(DirectionEnum direction, int currFloor){
        return this.controller.getElevator(direction, currFloor);
    }

    public void run(Elevator elevator){
        this.controller.run(elevator);
    }

}
