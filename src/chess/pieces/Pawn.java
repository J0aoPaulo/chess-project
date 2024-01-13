package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean verifyPosition(Position p) {
        boolean verifyPositionExist = getBoard().positionExists(p.getRow(), p.getColumn());

        return verifyPositionExist && !getBoard().thereIsAPiece(p);
    }

    private boolean verifyPositionEnemy(Position p) {
      return getBoard().positionExists(p.getRow(), p.getColumn()) && isThereOpponentPiece(p);
    }

    private void makeOneMove(Position p, boolean[][] mat) {
        if(verifyPosition(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    private void makeTwoMove(Position p, Position p2, boolean[][] mat) {
        if(verifyPosition(p) && verifyPosition(p2) && getMoveCount() == 0)
            mat[p.getRow()][p.getColumn()] = true;

    }

    private void moveToEnemy(Position p, boolean[][] mat) {
        if(verifyPositionEnemy(p) && isThereOpponentPiece(p))
            mat[p.getRow()][p.getColumn()] = true;

    }

    private boolean enPassantAvailable(Position p) {
        return getBoard().piece(p.getRow(), p.getColumn()) == chessMatch.getEnPassantVulnerable();
    }

    private void verifyEnPassantWhite(Position p, boolean[][] mat) {
        if(p.getRow() == 3) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if(verifyPositionEnemy(left) && enPassantAvailable(p)) {
                mat[left.getRow() - 1][left.getColumn()] = true;
            }
            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if(verifyPositionEnemy(right) && enPassantAvailable(right)) {
                mat[right.getRow() - 1][right.getColumn()] = true;
            }
        }
    }

    private void verifyEnPassantBlack(Position p, boolean[][] mat) {
        if(p.getRow() == 4) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if(verifyPositionEnemy(left) && enPassantAvailable(p)) {
                mat[left.getRow() + 1][left.getColumn()] = true;
            }
            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if(verifyPositionEnemy(right) && enPassantAvailable(right)) {
                mat[right.getRow() + 1][right.getColumn()] = true;
            }
        }
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

            verifyEnPassantWhite(position, mat);
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            makeOneMove(p, mat);

            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(p.getRow() - 1, p.getColumn());
            makeTwoMove(p, p2, mat);

            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            moveToEnemy(p, mat);

            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            moveToEnemy(p, mat);

            verifyEnPassantBlack(position, mat);
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}