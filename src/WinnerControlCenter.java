public class WinnerControlCenter {

    int winCondition;
    boolean result;

    public WinnerControlCenter() {

    }

    public boolean winnerControl(int matrixBoard, int[][] board, int vertical, int horizontal, int player) {

        winCondition = matrixBoard;

        //dikey kontrol
        int winVertical = 0;
        for (int i = 0; i < matrixBoard; i++) {
            if (board[i][horizontal - 1] == player) {
                winVertical++;
            }
        }

        //yatay kontrol
        int winHorizontal = 0;
        for (int i = 0; i < matrixBoard; i++) {
            if (board[vertical - 1][i] == player) {
                winHorizontal++;
            }
        }

        //çapraz sol kontrol
        int crossLeft = 0;
        int horizontalLeft = 0;
        for (int i = 0; i < matrixBoard; i++) {
            if (board[i][horizontalLeft] == player) {
                crossLeft++;
            }
            horizontalLeft++;
        }

        //çapraz sağ kontrol
        int crossRight = 0;
        int horizonRight = matrixBoard - 1;
        for (int i = 0; i < matrixBoard; i++) {
            if (board[i][horizonRight] == player) {
                crossRight++;
            }
            horizonRight--;
        }

        result = winVertical == winCondition || winHorizontal == winCondition || crossRight == winCondition || crossLeft == winCondition;
        return result;
    }
}