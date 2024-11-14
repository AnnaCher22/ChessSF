public class Horse extends ChessPiece {


    public Horse(String color) {
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

        // Проверка на "ход коня" (буквой Г)
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            // Проверка, что целевая ячейка свободна или занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true; // Конь может двигаться
            }
        }
        return false; // Конь не может двигаться
    }




    @Override
    public String getSymbol() {
        return "H";
    }
}