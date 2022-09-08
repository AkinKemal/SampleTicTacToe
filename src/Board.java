import java.util.Random;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    MotionControlCenter motionControlCenter = new MotionControlCenter();

    //color
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    int randomNumberVertical, randomNumberHorizontal, vertical, horizontal;
    boolean controlInside, controlEmpty = true, computerControlInside, computerControlEmpty;

    public Board() {

    }

    //oyun tahtası sıfırlama
    public void gameBoardReset(int[][] board, int matrixBoard) {
        for (int i = 0; i < matrixBoard; i++) {
            for (int j = 0; j < matrixBoard; j++) {
                board[i][j] = 0;
            }
        }
    }

    //input kotrol etme
    public int[][] motionControl(int matrixBoard, int[][] board, int player) {

        System.out.println("(First enter vertical and then horizontal matrix)");
        System.out.print("Please enter your matrix for the movement: ");
        do {
            vertical = scanner.nextInt();
            horizontal = scanner.nextInt();

            controlInside = motionControlCenter.isInputInsideMatrix(matrixBoard, vertical, horizontal);
            if (!controlInside) {
                System.out.println(ANSI_RED + "WARNING! " + ANSI_RESET + "You entered a ınput other than the game board.");
                System.out.println("Please Try Again (matrix of game board: " + matrixBoard + "x" + matrixBoard + " )");
            }
            if (controlInside) {
                controlEmpty = motionControlCenter.isMatrixEmpty(board, vertical, horizontal);
                if (!controlEmpty) {
                    System.out.println(ANSI_RED + "WARNING! " + ANSI_RESET + "Unfortunately the matrix is full. Unfortunately the matrix is full.");
                    System.out.println("Please Try Again");
                }
            }
        } while (!controlInside || !controlEmpty);
        moveHasBeenMade(board, player);
        motionControlCenter.controlReset();
        valueShow(vertical, horizontal);
        return board;
    }

    //bilgisayar input oluşturma
    public int[][] computerMotion(int matrixBoard, int[][] board, int player) {

        do {
            randomNumberVertical = random.nextInt(matrixBoard) + 1;
            randomNumberHorizontal = random.nextInt(matrixBoard) + 1;
            computerControlInside = motionControlCenter.isInputInsideMatrix(matrixBoard, randomNumberVertical, randomNumberHorizontal);

            if (computerControlInside) {
                computerControlEmpty = motionControlCenter.isMatrixEmpty(board, randomNumberVertical, randomNumberHorizontal);
            }
        } while (!computerControlInside || !computerControlEmpty);
        board[randomNumberVertical - 1][randomNumberHorizontal - 1] = player;
        motionControlCenter.controlReset();
        valueShow(randomNumberVertical, randomNumberHorizontal);
        return board;
    }

    //hareketi oyun tahtasına kaydetme
    public void moveHasBeenMade(int[][] board, int player) {
        board[vertical - 1][horizontal - 1] = player;
    }

    //oyun tahtasını yazdırma
    public void gameBoardWriting(int[][] board, int matrixBoard) {
        System.out.println();
        for (int i = 0; i < matrixBoard; i++) {
            System.out.print(" | ");
            for (int j = 0; j < matrixBoard; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" ");
                } else if (board[i][j] == 1) {
                    System.out.print(ANSI_CYAN + "X" + ANSI_RESET);
                } else if (board[i][j] == 2) {
                    System.out.print(ANSI_YELLOW + "O" + ANSI_RESET);
                } else {
                    System.out.print("W");
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public void design() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public void winWriting(String playerName) {
        System.out.println(playerName + " win the game.");
    }

    public void valueShow(int valueVertical, int valueHorizontal) {
        System.out.println(ANSI_PURPLE + "( " + valueVertical + "-" + valueHorizontal + " )" + ANSI_RESET);
    }
}