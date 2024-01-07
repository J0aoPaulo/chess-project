package chess;

import boardgame.BoardException;
import boardgame.Position;

public class ChessPosition {

    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row < 0 || row > 8) {
            throw new BoardException("Error instantiating ChessPosition. Valid values are form A1 to H8");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' - position.getColumn()), (position.getRow()) - 8);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(column);
        sb.append(row);

        return sb.toString();
    }
}