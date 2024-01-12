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

    private boolean verifyPosition(Position p) {
        boolean thisPositionExist = getBoard().positionExists(p.getRow(), p.getColumn());

        return thisPositionExist && !getBoard().thereIsAPiece(p);
    }

    private void moveAbove(Position p, boolean[][] mat) {
        while (verifyPosition(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn());
        }
    }

    private void moveBelow(Position p, boolean[][] mat) {
        while (verifyPosition(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn());
        }
    }

    private void moveRight(Position p, boolean[][] mat) {
        while (verifyPosition(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow(), p.getColumn() + 1);
        }
    }

    private void moveLeft(Position p, boolean[][] mat) {
        while (verifyPosition(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow(), p.getColumn() - 1);
        }
    }

    private void defeatEnemy(Position p, boolean[][] mat) {
        if (getBoard().positionExists(p.getRow(), p.getColumn()) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves () {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        p.setValues(position.getRow() - 1, position.getColumn());
        moveAbove(p, mat);
        defeatEnemy(p, mat);

        p.setValues(position.getRow() + 1, position.getColumn());
        moveBelow(p, mat);
        defeatEnemy(p, mat);

        p.setValues(position.getRow(), position.getColumn() - 1);
        moveLeft(p, mat);
        defeatEnemy(p, mat);

        p.setValues(position.getRow(), position.getColumn() + 1);
        moveRight(p, mat);
        defeatEnemy(p, mat);

        return mat;
    }
}