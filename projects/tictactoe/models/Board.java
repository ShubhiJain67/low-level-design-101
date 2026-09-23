package projects.tictactoe.models;

import projects.tictactoe.enums.*;

public class Board {
    private final int size;
    private final Cell[][] board;
    private int availableEmptyCells;

    public Board(int size){
        this.size = size;
        this.board = new Cell[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Cell(i*size + j);
            }
        }
        this.availableEmptyCells = size * size;
    }

    public boolean isValidCellPosition(int row, int col){
        return !(row < 0 || col <0 || row >= this.size || col >= this.size);
    }

    public CellType getCellType(int row, int col){
        if(!this.isValidCellPosition(row, col)){
            System.err.println("invalid row and col found, cannot tell cell type.");
            return null;
        }
        return this.board[row][col].getType();
    }

    public void placePiece(int row, int col, CellType type) {
        if(!this.isValidCellPosition(row, col)){
            System.err.println("invalid row and col found, cannot set cell type.");
            return;
        }
        this.board[row][col].setType(type);
        this.availableEmptyCells -= 1;
    }

    public boolean isBoardFull(){
        return this.availableEmptyCells == 0;
    }

    public int getSize(){
        return this.size;
    }
}
