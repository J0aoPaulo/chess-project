package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

    private boolean verifyNullPosition(Position p) {
        return getBoard().piece(p.getRow(), p.getColumn()) == null;
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position.getRow(), position.getColumn());
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    private void castlingRightSide(boolean [][] mat) {
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
            Position postT1 = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(postT1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if(verifyNullPosition(p1) && verifyNullPosition(p2)) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
        }
    }

    private void castlingLeftSide(boolean[][] mat) {
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
            Position postT2 = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(postT2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if(verifyNullPosition(p1) && verifyNullPosition(p2) && verifyNullPosition(p3)) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
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

        // Special moves
        castlingRightSide(mat);
        castlingLeftSide(mat);

        return mat;
    }
}