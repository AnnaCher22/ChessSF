public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        // Проверка на "ход слона" (диагонально)
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if (deltaX == deltaY) {
            // Проверка на препятствия между начальной и целевой позицией
            int stepX = (toLine - line) / deltaX;
            int stepY = (toColumn - column) / deltaY;

            for (int i = 1; i < deltaX; i++) {
                if (chessBoard.board[line + i * stepX][column + i * stepY] != null) {
                    return false; // Есть препятствие
                }
            }

            // Проверка, что целевая ячейка свободна или занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true; // Слон может двигаться
            }
        }
        return false; // Слон не может двигаться
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}