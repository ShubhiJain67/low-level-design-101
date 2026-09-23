package projects.tictactoe.models;

import projects.tictactoe.enums.CellType;

public class Cell {
    private final int id;
    private CellType type;

    public Cell(int id){
        this.id = id;
        this.type = CellType.EMPTY;
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
}
