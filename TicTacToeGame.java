  import java.util.Scanner;

    public class TicTacToeGame {
    
        private static final char EMPTY = ' ';
        private static final char PLAYER_X = 'X';
        private static final char PLAYER_O = 'O';
        private static char[][] board = new char[3][3];
        private static char currentPlayer = PLAYER_X;
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String playAgain;
    
            do {
                initializeBoard();
                playGame(scanner);
                System.out.print("Do you want to play again? (yes/no): ");
                playAgain = scanner.next();
            } while (playAgain.equalsIgnoreCase("yes"));
    
            System.out.println("Thank you for playing!");
            scanner.close();
        }
    
        // Initialize the game board with empty spaces
        private static void initializeBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = EMPTY;
                }
            }
            currentPlayer = PLAYER_X;
        }
    
        // Display the current game board
        private static void displayBoard() {
            System.out.println("  0 1 2");
            for (int i = 0; i < 3; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j]);
                    if (j < 2) System.out.print("|");
                }
                System.out.println();
                if (i < 2) System.out.println("  -----");
            }
        }
    
        // Play the game by taking turns for each player
        private static void playGame(Scanner scanner) {
            boolean gameWon = false;
            int movesCount = 0;
    
            while (!gameWon && movesCount < 9) {
                displayBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
    
                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    movesCount++;
    
                    if (checkWin()) {
                        displayBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameWon = true;
                    } else if (movesCount == 9) {
                        displayBoard();
                        System.out.println("The game is a draw!");
                    } else {
                        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                    }
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            }
        }
    
        // Check if the move is valid (within bounds and on an empty cell)
        private static boolean isValidMove(int row, int col) {
            return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
        }
    
        // Check if the current player has won the game
        private static boolean checkWin() {
            // Check rows, columns, and diagonals
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
                if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
            }
            if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
            if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
            return false;
        }
    }
    
