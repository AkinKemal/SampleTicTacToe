import java.util.Scanner;

public class Main {

    //color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";

    static int type, numberOfMoves;
    static boolean userFollowControlInstructions = true, userFollowControlInstructionsControl = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board boardOperations = new Board();
        WinnerControlCenter winnerControlCenter = new WinnerControlCenter();
        Result result = new Result();

        String player1, player2;
        int matrixBoard, player1Number = 1, player2Number = 2, win1 = 0, win2 = 0;
        boolean controlX, controlO = false;

        boardOperations.design();
        System.out.println("Press 1 to play for 2 players\nPress 2 to play with the computer");
        System.out.print(ANSI_RED + "->" + ANSI_RESET);
        type = scanner.nextInt();
        boardOperations.design();
        System.out.print("Please enter player1 name: ");
        player1 = scanner.next();
        if (type == 1) {
            System.out.print("Please enter player2 name: ");
            player2 = scanner.next();
        } else {
            player2 = "computer";
        }

        System.out.println("Please enter size of the game board's matrix: ");
        System.out.print(ANSI_RED + "->" + ANSI_RESET);
        do {
            matrixBoard = scanner.nextInt();

            if (matrixBoard < 3) {
                System.out.println(ANSI_RED + "WARNING! " + ANSI_RESET + "Size of the game board's matrix cannot be less than 3");
                System.out.println("Please Try Again etc. 3, 4, 5...");
            }
        } while (matrixBoard < 3);

        //oyun tahtasını oluşturma
        int[][] board = new int[matrixBoard][matrixBoard];
        numberOfMoves = (matrixBoard * matrixBoard);
        //oyun tahtasını sıfırlama
        boardOperations.gameBoardReset(board, matrixBoard);
        boardOperations.design();

        System.out.println("Tic Tac Toe game is loading...");
        boardOperations.design();

        int choice;
        do {
            do {
                board = getInstructions(boardOperations, board, matrixBoard, player1, player1Number);
                controlX = getControl(boardOperations, winnerControlCenter, board, matrixBoard, player1Number);
                numberOfMoves--;
                if (!controlX && numberOfMoves > 0) {
                    board = getInstructions(boardOperations, board, matrixBoard, player2, player2Number);
                    controlO = getControl(boardOperations, winnerControlCenter, board, matrixBoard, player2Number);
                    numberOfMoves--;
                }
                if (controlX) {
                    boardOperations.winWriting(player1);
                    win1++;
                }
                if (controlO) {
                    boardOperations.winWriting(player2);
                    win2++;
                }
            } while (!controlX && !controlO && numberOfMoves > 0);
            boardOperations.gameBoardWriting(board, matrixBoard);
            boardOperations.design();
            System.out.println("Press 1 to play the game again\nPress 2 to see the results");
            System.out.print(ANSI_RED + "->" + ANSI_RESET);
            choice = scanner.nextInt();
            boardOperations.design();
            boardOperations.gameBoardReset(board, matrixBoard);
            numberOfMoves = (matrixBoard * matrixBoard);
            userFollowControlInstructions = true;
            userFollowControlInstructionsControl = true;

        } while (choice == 1);
        result.resultPrinting(player1, player2, win1, win2);
    }

    private static int[][] getInstructions(Board boardOperations, int[][] board, int matrixBoard, String player, int playerNumber) {
        System.out.println(ANSI_BG_BLUE + player + ANSI_RESET + " it is your turn to move.");
        if (type == 1 || userFollowControlInstructions) {
            boardOperations.gameBoardWriting(board, matrixBoard);
            board = boardOperations.motionControl(matrixBoard, board, playerNumber);
            userFollowControlInstructions = false;
        } else {
            board = boardOperations.computerMotion(matrixBoard, board, playerNumber);
            userFollowControlInstructions = true;
        }
        return board;
    }

    private static boolean getControl(Board boardOperations, WinnerControlCenter winnerControlCenter, int[][] board, int matrixBoard, int playerNumber) {
        boolean control;
        if (type == 1 || userFollowControlInstructionsControl) {
            control = winnerControlCenter.winnerControl(matrixBoard, board, boardOperations.vertical, boardOperations.horizontal, playerNumber);
            userFollowControlInstructionsControl = false;
        } else {
            control = winnerControlCenter.winnerControl(matrixBoard, board, boardOperations.randomNumberVertical, boardOperations.randomNumberHorizontal, playerNumber);
            userFollowControlInstructionsControl = true;
        }
        boardOperations.design();
        return control;
    }
}