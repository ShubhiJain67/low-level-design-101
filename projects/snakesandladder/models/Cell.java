package projects.snakesandladder.models;

import projects.snakesandladder.enums.CellType;

public class Cell {
    private final int id;
    private Cell nextCell;
    private CellType type;

    public Cell(int id, CellType type) {
        this.id = id;
        this.nextCell = null;
        this.type = type;
    }

    public int getId(){
        return this.id;
    }

    public CellType getType(){
        return this.type;
    }

    public void setType(CellType type){
        this.type = type;
    }

    public Cell getNextCell(){
        return this.nextCell;
    }

    public void setNextCell(Cell nextCell){
        this.nextCell = nextCell;
    }
}
