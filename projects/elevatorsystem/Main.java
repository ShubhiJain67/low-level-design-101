package projects.elevatorsystem;

public class Main {
    public static void main(String[] args) {
        Building building = new Building();
        for (int floor = 0; floor < 10; floor++) {
            building.addFloor(floor);
        }
        for (int elevatorId = 0; elevatorId < 2; elevatorId++) {
            Elevator elevator = new Elevator(elevatorId);
            building.addElevator(elevator);
        }
        Elevator elevator = building.getElevator(DirectionEnum.UP, 0);
        building.run(elevator);
    }
}
