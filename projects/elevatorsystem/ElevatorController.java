package projects.elevatorsystem;

import java.util.*;

public class ElevatorController {
    private Building building;
    private final Set<Elevator> movingElevators;

    public ElevatorController() {
        this.building = null;
        this.movingElevators = new TreeSet<>();
    }

    public void setBuilding(Building building){
        this.building = building;
    }

    public Elevator getElevator(DirectionEnum direction, int currentFloor) {
        Elevator closestElevator = null;
        int closestDistance = this.building.getFloors().size() + 1;
        for (Elevator elevator : this.building.getElevators()) {
            int distance = elevator.getCurrentFloor() - currentFloor;
            if(distance > 0 && closestDistance > distance){
                closestDistance = distance;
                closestElevator = elevator;
            }
        }
        return closestElevator;
    }

    public void run(Elevator elevator){
        this.movingElevators.add(elevator);
        this.runElevators();
    }

    public void runElevators(){
        List<Elevator> availableElevators = (List<Elevator>) this.movingElevators;
        while(!availableElevators.isEmpty()){
            List<Elevator> newAvailableElevators = new ArrayList<>();
            for (Elevator elevator : availableElevators) {
                int currFloor = elevator.getCurrentFloor();
                if(elevator.hasStop(currFloor)){
                    elevator.halt(currFloor);
                } else {
                    elevator.setCurrentFloor(currFloor-1);
                }
                if(elevator.hasStops()){
                    newAvailableElevators.add(elevator);
                }
            }
            availableElevators = newAvailableElevators;
        }
    }
}
