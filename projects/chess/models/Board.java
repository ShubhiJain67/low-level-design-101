package projects.chess.models;

import java.util.UUID;
import projects.chess.enums.ChessPiece;
import projects.chess.enums.Team;

public class Board {
    private final String id;
    private final Cell[][] cells;
    private final static int BOARD_SIZE = 8;

    public Board(){
        this.id = UUID.randomUUID().toString();
        this.cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        this.buildBoard();
    }

    private void buildBoard(){
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.cells[row][col] = new Cell(row * BOARD_SIZE + col);
            }
        }
        this.placeInitialPieces(Team.BLACK);
        this.placeInitialPieces(Team.WHITE);
    }

    private void placeInitialPieces(Team team){
        int rank = team == Team.WHITE ? 0 : BOARD_SIZE - 1;
        for (int file = 0; file < BOARD_SIZE; file++) {
            ChessPiece piece;
            switch (file) {
                case 0, 7 -> piece = ChessPiece.ROOK;
                case 1, 6 -> piece = ChessPiece.KNIGHT;
                case 2, 5 -> piece = ChessPiece.BISHOP;
                case 3 -> piece = ChessPiece.QUEEN;
                case 4 -> piece = ChessPiece.KING;
                default -> piece = ChessPiece.NONE;
            }
            this.cells[rank][file].setPiece(piece);
            this.cells[rank][file].setTeam(team);
        }
        rank = team == Team.WHITE ? 1 : BOARD_SIZE - 2;
        for (int file = 0; file < BOARD_SIZE; file++) {
            this.cells[rank][file].setPiece(ChessPiece.PAWN);
            this.cells[rank][file].setTeam(team);
        }
    }

    public String getId(){
        return this.id;
    }

    private void removePiece(int row, int col){
        if(!this.isValidCell(row, col)){
            return;
        }
        if(this.cells[row][col].getPiece() == ChessPiece.NONE){
            System.err.println("There is no piece present on (" + row + "," + col + ")");
            return;
        }
        this.cells[row][col].setPiece(ChessPiece.NONE);
        this.cells[row][col].setTeam(Team.NONE);
    }

    public void placePiece(int row, int col, ChessPiece piece, Team team){
        if(!this.isValidCell(row, col)){
            return;
        }
        ChessPiece currPiece = this.cells[row][col].getPiece();
        Team currTeam = this.cells[row][col].getTeam();
        if(currPiece != ChessPiece.NONE){
            if(currTeam == team){
                System.err.println("Cannot place a piece where your own piece is there. -> " + currPiece.name() + " on (" + row + "," + col + ")");
                return;
            }
            System.err.println("Evicting " + currPiece.name() + " from (" + row + "," + col + ")");
            this.removePiece(row, col);
        }
        System.err.println("Placing " + piece.name() + " on (" + row + "," + col + ")");
        this.cells[row][col].setPiece(piece);
        this.cells[row][col].setTeam(team);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol){
        ChessPiece piece = this.getPieceAt(fromRow, fromCol);
        Team team = this.getTeamAt(fromRow, fromCol);
        this.placePiece(toRow, toCol, piece, team);
        this.removePiece(fromRow, fromCol);
    }

    public boolean isPathClear(int fromRow, int fromCol, int toRow, int toCol){
        int rowStep = Integer.compare(toRow, fromRow);
        int colStep = Integer.compare(toCol, fromCol);
        int row = fromRow + rowStep;
        int col = fromCol + colStep;
        while (row != toRow || col != toCol) {
            if (this.getTeamAt(row, col) != Team.NONE) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    private boolean isValidCell(int row, int col){
        if (row < 0 || col < 0 || row >= BOARD_SIZE || col >= BOARD_SIZE) {
            System.err.println("Found invalid row | col -> (" + row + "," + col + ")" );
            return false;
        }
        return true;
    }

    public Team getTeamAt(int row, int col) {
        return this.cells[row][col].getTeam();
    }

    public ChessPiece getPieceAt(int row, int col) {
        return this.cells[row][col].getPiece();
    }

    public int getSize(){
        return BOARD_SIZE;
    }
}
