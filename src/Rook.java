public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }


        if (line == toLine || column == toColumn) {

            int stepX = (toLine - line) == 0 ? 0 : (toLine - line) / Math.abs(toLine - line);
            int stepY = (toColumn - column) == 0 ? 0 : (toColumn - column) / Math.abs(toColumn - column);

            for (int i = 1; i < Math.max(Math.abs(toLine - line), Math.abs(toColumn - column)); i++) {
                if (chessBoard.board[line + i * stepX][column + i * stepY] != null) {
                    return false;
                }
            }

            // Проверка, что целевая ячейка свободна или занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true; // Ладья может двигаться
            }
        }
        return false; // Ладья не может двигаться
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

