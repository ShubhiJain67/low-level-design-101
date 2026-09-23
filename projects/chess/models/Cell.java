package projects.chess.models;

import projects.chess.enums.*;

public class Cell {
    private final int id;
    private ChessPiece piece;
    private Team team;
    
    public Cell(int id){
        this.id = id;
        this.piece = ChessPiece.NONE;
        this.team = Team.NONE;
    }

    public int getId(){
        return this.id;
    }

    public ChessPiece getPiece(){
        return this.piece;
    }

    public void setPiece(ChessPiece piece){
        this.piece = piece;
    }

    public Team getTeam(){
        return this.team;
    }

    public void setTeam(Team team){
        this.team = team;
    }
}
