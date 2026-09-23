package projects.chess.strategy;

import java.util.EnumMap;
import java.util.Map;
import projects.chess.enums.ChessPiece;

public class MoveStrategyFactory {
    private static final Map<ChessPiece, IMoveStrategy> STRATEGIES = new EnumMap<>(ChessPiece.class);

    static {
        STRATEGIES.put(ChessPiece.PAWN, new PawnMoveStrategy());
        STRATEGIES.put(ChessPiece.KNIGHT, new KnightMoveStrategy());
        STRATEGIES.put(ChessPiece.ROOK, new RookMoveStrategy());
        STRATEGIES.put(ChessPiece.BISHOP, new BishopMoveStrategy());
        STRATEGIES.put(ChessPiece.QUEEN, new QueenMoveStrategy());
        STRATEGIES.put(ChessPiece.KING, new KingMoveStrategy());
    }
    

    public static IMoveStrategy getStrategy(ChessPiece piece){
        IMoveStrategy strategy = STRATEGIES.get(piece);
        if (strategy == null) {
            throw new IllegalArgumentException("No move strategy registered for piece " + piece);
        }
        return strategy;
    }
}
