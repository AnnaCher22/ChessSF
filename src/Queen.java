public class Queen extends ChessPiece {

    public Queen(String color) {
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

        // Проверка на "ход ферзя" (горизонтально, вертикально или диагонально)
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if (deltaX == 0 || deltaY == 0 || deltaX == deltaY) {
            // Проверка на препятствия между начальной и целевой позицией
            int stepX = (toLine - line) == 0 ? 0 : (toLine - line) / Math.abs(toLine - line);
            int stepY = (toColumn - column) == 0 ? 0 : (toColumn - column) / Math.abs(toColumn - column);

            for (int i = 1; i < Math.max(deltaX, deltaY); i++) {
                if (chessBoard.board[line + i * stepX][column + i * stepY] != null) {
                    return false; // Есть препятствие
                }
            }

            // Проверка, что целевая ячейка свободна или занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true; // Ферзь может двигаться
            }
        }
        return false; // Ферзь не может двигаться
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}