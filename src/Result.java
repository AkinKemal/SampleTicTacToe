public class Result {

    //color
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_LIGHT_YELLOW = "\u001B[93m";

    public Result() {

    }

    public void resultPrinting(String player1, String player2, int win1, int win2) {
        System.out.println(ANSI_RED + "|---------------------------------------------------------|" + ANSI_RESET);
        int helper;
        for (int j = 0; j < 6; j++) {
            for (int i = 1; i <= 59; i++) {
                if (59 % i == 0) {
                    System.out.print(ANSI_RED + "|" + ANSI_RESET);
                } else if (j == 0 && i == 23) {
                    String companyName = "DOFTDARE GAME";
                    System.out.print(ANSI_BOLD + ANSI_RED + companyName + ANSI_RESET);
                    helper = companyName.length() - 1;
                    i += helper;
                } else if (j == 2 && i == 4) {
                    System.out.print(player1.toUpperCase());
                    helper = player1.length() - 1;
                    i += helper;
                } else if (j == 3 && i == 4) {
                    System.out.print(player2.toUpperCase());
                    helper = player2.length() - 1;
                    i += helper;
                } else if (j == 2 && i == 33) {
                    String player1Win = "Player 1's total wins: " + win1;
                    System.out.print(player1Win);
                    helper = player1Win.length() - 1;
                    i += helper;
                } else if (j == 3 && i == 33) {
                    String player2Win = "Player 2's total wins: " + win2;
                    System.out.print(player2Win);
                    helper = player2Win.length() - 1;
                    i += helper;
                } else if (j == 4 && i == 24) {
                    String winner = "GAME WINNER";
                    System.out.print(winner);
                    helper = winner.length() - 1;
                    i += helper;
                } else if (j == 5 && i == 25) {
                    String player;
                    if (win1 > win2) {
                        player = "PLAYER 1!";
                    } else if (win1 == win2) {
                        player = "  DRAW!  ";
                    } else {
                        player = "PLAYER 2!";
                    }
                    System.out.print(ANSI_LIGHT_YELLOW + player + ANSI_RESET);
                    helper = player.length() - 1;
                    i += helper;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println(ANSI_RED + "|---------------------------------------------------------|" + ANSI_RESET);
    }
}