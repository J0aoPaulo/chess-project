package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position.getRow(), position.getColumn());
        return p == null || p.getColor() != getColor();
    }

    private void verifyMove(Position p, boolean[][] mat) {
        if (getBoard().positionExists(p.getRow(), p.getColumn()) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        p.setValues(position.getRow() - 1, position.getColumn());
        verifyMove(p, mat);

        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        verifyMove(p, mat);

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        verifyMove(p, mat);

        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        verifyMove(p, mat);

        // nw
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        verifyMove(p, mat);

        // ne
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        verifyMove(p, mat);

        // sw
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        verifyMove(p, mat);

        // se
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        verifyMove(p, mat);

        return mat;
    }
}