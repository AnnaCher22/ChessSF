public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        // Определение направления движения в зависимости от цвета
        int direction = color.equals("White") ? -1 : 1;
        int startLine = color.equals("White") ? 6 : 1;

        // Проверка на движение вперед
        if (toColumn == column) {
            // Проверка на первый ход (движение на 2 клетки)
            if (line == startLine && toLine == line + direction * 2 && chessBoard.board[line + direction][column] == null) {
                return true; // Пешка может двигаться на 2 клетки
            }
            // Проверка на движение на 1 клетку вперед
            if (toLine == line + direction && chessBoard.board[toLine][toColumn] == null) {
                return true; // Пешка может двигаться на 1 клетку
            }
        }
        // Проверка на захват противника
        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece != null && !targetPiece.getColor().equals(this.color)) {
                return true; // Пешка может атаковать
            }
        }
        return false; // Пешка не может двигаться
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
