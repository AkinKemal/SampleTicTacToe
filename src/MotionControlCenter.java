public class MotionControlCenter {

    boolean control, controlVertical, controlHorizontal;

    public MotionControlCenter() {

    }

    public boolean isInputInsideMatrix(int matrixBoard, int vertical, int horizontal) {
        controlVertical = vertical >= 1 && vertical <= matrixBoard;
        controlHorizontal = horizontal >= 1 && horizontal <= matrixBoard;
        control = controlHorizontal && controlVertical;
        return control;
    }

    public boolean isMatrixEmpty(int[][] board, int vertical, int horizontal) {
        control = board[vertical - 1][horizontal - 1] == 0;
        return control;
    }

    public void controlReset() {
        control = true;
    }
}