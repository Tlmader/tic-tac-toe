import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Contains the Main program logic for the TicTacToe game.
 *
 * @author Ted Mader
 * @date 2016-09-27
 */
public class TicTacToe {

  /**
   * Called at program startup.
   *
   * @param args an array of arguments
   */
  public static void main(String[] args) {
    gameLoop();
  }

  /**
   * Handles the game loop.
   */
  public static void gameLoop() {
    Board board = new Board();
    char player = 'O';
    boolean win = false;
    int turns = 0;
    System.out.println("Welcome to TicTacToe!\nGame begin!");
    while (!win && turns < 9) {
      System.out.println(board.toString());
      win = turn(board, player);
      if (win) {
        System.out.println(board.toString() + "\nPlayer " + player + " wins!");
      }
      if (player == 'O') {
        player = 'X';
      } else if (player == 'X') {
        player = 'O';
      }
      turns++;
    }
    if (!win) {
      System.out.println("Draw!");
    }
  }

  /**
   * Handles a single turn.
   *
   * @param board a Board to be modified
   * @param player a char representing the player
   * @return true if turn resulted in a win
   */
  public static boolean turn(Board board, char player) {
    System.out.println("=== Player " + player + "'s turn ===");
    Scanner sc = new Scanner(System.in);
    boolean success = false;
    while (!success) {
      System.out.println("Input 1-9 for move.");
      try {
        int move = sc.nextInt();
        sc.nextLine();
        success = board.applyMove(player, move);
      } catch (InputMismatchException ex) {
        sc.nextLine();
        System.out.println("Invalid input.");
      }
      if (!success) {
        System.out.println("Cell is occupied.");
      }
    }
    return board.checkIfWin();
  }
}

/**
 * Contains the fields and methods for Board objects.
 *
 * @author Ted Mader
 * @date 2016-09-27
 */
class Board {

  private char[] cells = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

  /**
   * Modifies a cell based on a player's move.
   *
   * @param player a player
   * @param move an index for the cell to be modified
   * @return true if unoccupied
   */
  public boolean applyMove(char player, int move) {
    move--;
    if (cells[move] != 'X' && cells[move] != 'O') {
      cells[move] = player;
      return true;
    }
    return false;
  }

  /**
   * Checks for a win; a row of three cells with equal values.
   *
   * @return true if win
   */
  public boolean checkIfWin() {
    if ((cells[0] == cells[1] && cells[1] == cells[2]) || // Horizontal
        (cells[3] == cells[4] && cells[4] == cells[5]) ||
        (cells[6] == cells[7] && cells[7] == cells[8]) ||
        (cells[0] == cells[3] && cells[3] == cells[6]) || // Vertical
        (cells[1] == cells[4] && cells[4] == cells[7]) ||
        (cells[2] == cells[5] && cells[5] == cells[8]) ||
        (cells[0] == cells[4] && cells[4] == cells[8]) || // Diagonal
        (cells[6] == cells[4] && cells[4] == cells[2])) {
      return true;
    }
    return false;
  }

  /**
   * Returns a String representation of the Board.
   *
   * @return the String for the Board
   */
  public String toString() {
    return cells[0] + "|" + cells[1] + "|" + cells[2] + "\n" +
           cells[3] + "|" + cells[4] + "|" + cells[5] + "\n" +
           cells[6] + "|" + cells[7] + "|" + cells[8];
  }
}
