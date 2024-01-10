package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    private void moveInDirection(Position p, boolean[][] mat, int rowChange, int colChange) {
        p.setValues(position.getRow() + rowChange, position.getColumn() + colChange);
        while (getBoard().positionExists(p.getRow(), p.getColumn()) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + rowChange, p.getColumn() + colChange);
        }
        if (getBoard().positionExists(p.getRow(), p.getColumn()) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        moveInDirection(p, mat, -1, 0); // Move above
        moveInDirection(p, mat, 1, 0);  // Move below
        moveInDirection(p, mat, 0, -1); // Move to left
        moveInDirection(p, mat, 0, 1);  // Move to right

        return mat;
    }
}
