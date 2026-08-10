import java.util.Scanner;

public class Main {

    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char AI_Y = 'O';

    private static final char[][] board = {
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY}
    };

    private static void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    private static int evaluateBoard() {

        // Rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] == board[r][1]
                    && board[r][1] == board[r][2]) {

                if (board[r][0] == AI_Y)
                    return +10;

                if (board[r][0] == PLAYER_X)
                    return -10;
            }
        }

        // Columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] == board[1][c]
                    && board[1][c] == board[2][c]) {

                if (board[0][c] == AI_Y)
                    return +10;

                if (board[0][c] == PLAYER_X)
                    return -10;
            }
        }

        // Main diagonal
        if (board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {

            if (board[0][0] == AI_Y)
                return +10;

            if (board[0][0] == PLAYER_X)
                return -10;
        }

        // Other diagonal
        if (board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {

            if (board[0][2] == AI_Y)
                return +10;

            if (board[0][2] == PLAYER_X)
                return -10;
        }

        return 0;
    }

    private static boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkGameOver() {

        int score = evaluateBoard();

        if (score == 10) {
            System.out.println("Computer Wins!!!!");
            return true;
        }

        if (score == -10) {
            System.out.println("You Win!!!!");
            return true;
        }

        if (!isMovesLeft()) {
            System.out.println("It's a Draw!!!!");
            return true;
        }

        return false;
    }

    private static int minimax(int depth, boolean isMax) {

        int score = evaluateBoard();

        if (score == 10)
            return score - depth;

        if (score == -10)
            return score + depth;

        if (!isMovesLeft())
            return 0;

        // Computer's turn
        if (isMax) {

            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == EMPTY) {

                        board[i][j] = AI_Y;

                        best = Math.max(
                                best,
                                minimax(depth + 1, false)
                        );

                        board[i][j] = EMPTY;
                    }
                }
            }

            return best;
        }

        // Human's turn
        else {

            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == EMPTY) {

                        board[i][j] = PLAYER_X;

                        best = Math.min(
                                best,
                                minimax(depth + 1, true)
                        );

                        board[i][j] = EMPTY;
                    }
                }
            }

            return best;
        }
    }

    private static int[] findBestMove() {

        int bestVal = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == EMPTY) {

                    board[i][j] = AI_Y;

                    int moveVal = minimax(0, false);

                    board[i][j] = EMPTY;

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private static void humanMove(Scanner in) {

        while (true) {

            System.out.print("Enter row (1-3): ");
            int row = in.nextInt();

            System.out.print("Enter column (1-3): ");
            int col = in.nextInt();

            row--;
            col--;

            if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                System.out.println("Invalid position! Try again.");
                continue;
            }

            if (board[row][col] != EMPTY) {
                System.out.println("That position is already occupied!");
                continue;
            }

            board[row][col] = PLAYER_X;
            break;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println(
            "-------- Tic Tac Toe: YOU vs COMPUTER --------"
        );

        printBoard();

        while (true) {

            // Human Turn
            System.out.println("Your Turn (X)");
            humanMove(in);

            printBoard();

            if (checkGameOver())
                break;

            // Computer Turn
            System.out.println("Computer is Thinking......");

            int[] aiMove = findBestMove();

            board[aiMove[0]][aiMove[1]] = AI_Y;

            printBoard();

            if (checkGameOver())
                break;
        }

        in.close();
    }
}