package projects.snakesandladder.models;

import java.util.ArrayList;
import java.util.List;
import projects.snakesandladder.enums.CellType;

public class Board {
    private final List<Cell> cells;
    private final int size;
    private final Cell start;
    private final Cell end;

    public Board(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        for (int id = 1; id <= size; id++) {
            this.cells.add(new Cell(id, CellType.EMPTY));
        }
        this.start = this.cells.getFirst();
        this.end = this.cells.getLast();
    }

    private boolean createsCycle(int id, int nextId) {
        Cell current = this.cells.get(nextId - 1);
        while (current != null && current.getNextCell() != null) {
            current = current.getNextCell();
            if (current == this.cells.get(id - 1)) {
                return true;
            }
        }
        return false;
    }

    public void addSnake(int id, int nextId){
        if(id < 1 ||  id > this.size) {
            throw new IllegalArgumentException("Cell Id should be in between 1 and " + this.size + " found " + id);
        }
        if(nextId < 1 ||  nextId > this.size) {
            throw new IllegalArgumentException("Next Cell Id should be in between 1 and " + this.size + " found " + nextId);
        }
        if(nextId >= id){
            throw new IllegalArgumentException("Next Cell Id should be less than current Id " + id + " found " + nextId);
        }
        if(this.createsCycle(id, nextId)) {
            throw new IllegalArgumentException("Adding this snake/ladder creates a cycle");
        }
        Cell cell = this.cells.get(id - 1);
        if (cell.getType() != CellType.EMPTY) {
            throw new IllegalArgumentException("Cell " + id + " already has a snake or ladder");
        }
        Cell nextCell = this.cells.get(nextId - 1);
        cell.setType(CellType.SNAKE);
        cell.setNextCell(nextCell);
    }

    public void addLadder(int id, int nextId){
        if(id < 1 ||  id > this.size) {
            throw new IllegalArgumentException("Cell Id should be in between 1 and " + this.size + " found " + id);
        }
        if(nextId < 1 ||  nextId > this.size) {
            throw new IllegalArgumentException("Next Cell Id should be in between 1 and " + this.size + " found " + nextId);
        }
        if(nextId <= id){
            throw new IllegalArgumentException("Next Cell Id should be more than current Id " + id + " found " + nextId);
        }
        if(this.createsCycle(id, nextId)) {
            throw new IllegalArgumentException("Adding this snake/ladder creates a cycle");
        }
        Cell cell = this.cells.get(id - 1);
        if (cell.getType() != CellType.EMPTY) {
            throw new IllegalArgumentException("Cell " + id + " already has a snake or ladder");
        }
        Cell nextCell = this.cells.get(nextId - 1);
        cell.setType(CellType.LADDER);
        cell.setNextCell(nextCell);
    }

    public Cell getStartCell(){
        return this.start;
    }

    public Cell getEndCell(){
        return this.end;
    }

    public Cell getCell(int index){
        return this.cells.get(index-1);
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEndCell(Cell cell){
        return cell == this.end;
    }
}
