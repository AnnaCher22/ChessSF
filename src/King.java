public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка на "ход короля" (один шаг в любом направлении)
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if ((deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0)) {
            // Проверка, что целевая ячейка свободна или занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true; // Король может двигаться
            }
        }
        return false; // Король не может двигаться
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
