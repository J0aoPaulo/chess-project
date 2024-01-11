package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    private boolean verifyPosition(Position p) {
        boolean verifyPositionExist = getBoard().positionExists(p.getRow(), p.getColumn());
        boolean verifyPieceInThisPosition = getBoard().thereIsAPiece(p);

        return verifyPositionExist && !verifyPieceInThisPosition;
    }

    private boolean verifyPositionEnemy(Position p) {
      return getBoard().positionExists(p.getRow(), p.getColumn()) && isThereOpponentPiece(p);
    }

    private boolean makeOneMove(Position p, boolean[][] mat) {
        if(verifyPosition(p))
            return mat[p.getRow()][p.getColumn()] = true;

        return false;
    }

    private boolean makeTwoMove(Position p, Position p2, boolean[][] mat) {
        if(verifyPosition(p) && verifyPosition(p2) && getMoveCount() == 0)
            return mat[p.getRow()][p.getColumn()] = true;

        return false;
    }

    private boolean moveToEnemy(Position p, boolean[][] mat) {
        if(getBoard().positionExists(p.getRow(), p.getColumn()) && isThereOpponentPiece(p))
           return mat[p.getRow()][p.getColumn()] = true;

        return false;
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if(getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            makeOneMove(p, mat);

            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(p.getRow() - 1, p.getColumn());
            makeTwoMove(p, p2, mat);

            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            moveToEnemy(p, mat);

            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            moveToEnemy(p, mat);
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            makeOneMove(p, mat);

            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(p.getRow() + 1, p.getColumn());
            makeTwoMove(p, p2, mat);

            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            moveToEnemy(p, mat);

            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            moveToEnemy(p, mat);
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}