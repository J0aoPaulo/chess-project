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
        if(!postionExists(row, column)) {
            throw new BoardException("Error: Position not on the board");
        }

        return pieces[row][column];
    }

    public Piece piecePosition(Position position) {
        if(!postionExists(position.getRow(), position.getColumn())) {
            throw new BoardException("Error: Position not on the board");
        }

        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("Error: There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public boolean postionExists(int row, int column) {
        boolean isRowValid = row >= 0 && row < rows;
        boolean isColumnValid = column >= 0 && column < columns;

        return isRowValid && isColumnValid;
    }

    public boolean thereIsAPiece(Position position) {
        if(!postionExists(position.getRow(), position.getColumn())) {
            throw new BoardException("Error: Position not on the board");
        }
        return piecePosition(position) != null;
    }
}