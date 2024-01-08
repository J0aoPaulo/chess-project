package boardgame;

public class Board {

    private final int rows;
    private final int columns;

    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            throw new IllegalArgumentException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        verifyPosition(row, column);
        return pieces[row][column];
    }

    public Piece piecePosition(Position position) {
        verifyPosition(position.getRow(), position.getColumn());
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("Error: There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        verifyPosition(position.getRow(), position.getColumn());
        if(piecePosition(position) == null) {
            return null;
        }
        Piece aux = piecePosition(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    public boolean positionExists(int row, int column) {
        boolean isRowValid = row >= 0 && row < rows;
        boolean isColumnValid = column >= 0 && column < columns;

        return isRowValid && isColumnValid;
    }

    public void verifyPosition(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Error: Position not on the board");
        }
    }

    public boolean thereIsAPiece(Position position) {
        verifyPosition(position.getRow(), position.getColumn());
        return piecePosition(position) != null;
    }
}