public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

        public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {

        if (checkPos(startLine) && checkPos(startColumn)) {

            if (board[startLine][startColumn] == null || !nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }

            // Проверка возможности перемещения фигуры
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Перемещение фигуры
                board[endLine][endColumn] = board[startLine][startColumn]; // Если фигура может двигаться, мы перемещаем фигуру
                board[startLine][startColumn] = null; // Устанавливаем null в предыдущую ячейку

                // Обновляем переменные check для королей и ладей
                if (board[endLine][endColumn] instanceof King) {
                    ((King) board[endLine][endColumn]).setCheck(false);
                } else if (board[endLine][endColumn] instanceof Rook) {
                    ((Rook) board[endLine][endColumn]).setCheck(false);
                }

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White"; // Смена игрока
                return true;
            } else {
                return false; // Фигура не может переместиться
            }
        } else {
            return false; // Неверная начальная позиция
        }
    }

    public boolean castling0() {
        // Логика рокировки для 0 столбца
        int kingLine = nowPlayer.equals("White") ? 7 : 0; // Линия короля
        int rookLine = nowPlayer.equals("White") ? 7 : 0; // Линия ладьи
        int kingColumn = 4; // Колонка короля
        int rookColumn = 0; // Колонка ладьи

        // Проверка, что король и ладья не двигались
        if (board[kingLine][kingColumn] instanceof King && board[rookLine][rookColumn] instanceof Rook) {
            King king = (King) board[kingLine][kingColumn];
            Rook rook = (Rook) board[rookLine][rookColumn];

            if (!king.isCheck() && !rook.isCheck() && king.canMoveToPosition(this, kingLine, kingColumn, kingLine, kingColumn - 2) &&
                    board[kingLine][kingColumn - 1] == null && board[kingLine][kingColumn - 2] == null) {
                // Выполнение рокировки
                board[kingLine][kingColumn - 2] = king; // Перемещение короля
                board[kingLine][kingColumn] = null; // Удаление короля с предыдущей позиции
                board[kingLine][rookColumn + 1] = rook; // Перемещение ладьи
                board[rookLine][rookColumn] = null; // Удаление ладьи с предыдущей позиции
                return true;
            }
        }
        return false;
    }

    public boolean castling7() {
        // Логика рокировки для 7 столбца
        int kingLine = nowPlayer.equals("White") ? 7 : 0; // Линия короля
        int rookLine = nowPlayer.equals("White") ? 7 : 0; // Линия ладьи
        int kingColumn = 4; // Колонка короля
        int rookColumn = 7; // Колонка ладьи

        // Проверка, что король и ладья не двигались
        if (board[kingLine][kingColumn] instanceof King && board[rookLine][rookColumn] instanceof Rook) {
            King king = (King) board[kingLine][kingColumn];
            Rook rook = (Rook) board[rookLine][rookColumn];

            if (!king.isCheck() && !rook.isCheck() && king.canMoveToPosition(this, kingLine, kingColumn, kingLine, kingColumn + 2) &&
                    board[kingLine][kingColumn + 1] == null) {
                // Выполнение рокировки
                board[kingLine][kingColumn + 2] = king; // Перемещение короля
                board[kingLine][kingColumn] = null; // Удаление короля с предыдущей позиции
                board[kingLine][rookColumn - 1] = rook; // Перемещение ладьи
                board[rookLine][rookColumn] = null; // Удаление ладьи с предыдущей позиции
                return true;
            }
        }
        return false;
    }


    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
