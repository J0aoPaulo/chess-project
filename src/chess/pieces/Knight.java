package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position.getRow(), position.getColumn());
        return p == null || p.getColor() != getColor();
    }

    private boolean verifyPosition(Position p) {
        return getBoard().positionExists(p.getRow(), p.getColumn());
    }

    private void moveNorthwest(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = true;
    }

    private void moveNortheast(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = true;
    }

    private void moveSouthwest(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = true;
    }

    private void moveSoutheast(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = true;
    }

    private void moveRightAbove(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    private void moveRightBelow(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    private void moveLeftAbove(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    private void moveLeftBelow(Position p, boolean[][] mat) {
        if(verifyPosition(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        moveNorthwest(p, mat);

        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        moveNortheast(p, mat);

        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        moveSouthwest(p, mat);

        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        moveSoutheast(p, mat);

        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        moveRightAbove(p, mat);

        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        moveRightBelow(p, mat);

        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        moveLeftAbove(p, mat);

        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        moveLeftBelow(p, mat);

        return mat;
    }

    public String toString() {
        return "N";
    }
}