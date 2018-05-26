package View;

import java.util.InputMismatchException;
import java.util.Scanner;

import Model.Board.Board;
import Model.Cells.ICell;
import Model.Position.Position;

import static Model.Board.Board.NUMBER_OF_COLUMNS;
import static Model.Board.Board.NUMBER_OF_ROWS;

/**
 * Represents the methods that will print to the terminal.
 */
public class Console {

  /**
   * Creates a new console.
   */
  public Console() {
  }

  /**
   * Print the board to the terminal.
   *
   * @param board the Tic-Tac-Toe board to be printed.
   */
  public void printBoard(Board board) {
    System.out.printf("%n");
    System.out.println("    0     1     2");
    for (int i = 0; i < 3; i++) {
      System.out.println("       |     |     ");
      System.out.printf("%d ", i);
      for (int j = 0; j < 3; j++) {
        if (j != 0) {
          System.out.print("|");
        } else {
          System.out.print("");
        }
        Position position = new Position(i, j);
        String cellString = getCellString(board, position);
        System.out.print(cellString);
        System.out.print("");
      }
      System.out.printf("%n");
      if (i != 2) {
        System.out.println("  _____|_____|_____");
      } else {
        System.out.println("       |     |     ");
      }
    }
    System.out.printf("%n");
  }

  /**
   * Indicates the type of cell that is printing.
   *
   * @param board    the Tic-Tac-Toe board to be printed.
   * @param position the row and column of the cell to be printed.
   * @return A string specific to the cell type that occupies position on the board.
   */
  private String getCellString(Board board, Position position) {
    ICell cell = board.getCellAtPosition(position);
    if (cell.isOpen()) {
      return "     ";
    } else if (cell.isPlayerCell()) {
      return "  X  ";
    } else {
      return "  O  ";
    }
  }

  /**
   * Prints a message when the player wins.
   */
  public void playerWon() {
    System.out.println("Congratulations, YOU WON!!!!!");
  }

  /**
   * Prints a message when the computer wins.
   */
  public void computerWon() {
    System.out.println("Bummer, the computer won :( better luck next time");
  }

  /**
   * Prints a message when the player and computer tie.
   */
  public void tie() {
    System.out.println("It's a draw!! Well, at least you didn't lose.");
  }

  /**
   * Prints a message to the player asking for the row they would like to play at, takes the input,
   * validates the input. If the input is invalid, asked the user to type a valid input.
   */
  public int getRowFromPlayer() {
    int row = 0;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Please enter a row");

    try {

      row = scanner.nextInt();

    } catch (InputMismatchException ImE) {
      System.out.println("That was not a valid row, please enter a number from 0 to 2");
      getRowFromPlayer();
    }

    if (row >= 0 && row < NUMBER_OF_ROWS) {
      return row;
    } else {
      System.out.println("That was not a valid row, please enter a number from 0 to 2");
      getRowFromPlayer();
    }
    return row;
  }

  /**
   * Prints a message to the player asking for the column they would like to play at, takes the
   * input, validates the input. If the input is invalid, asked the user to type a valid input.
   */
  public int getColumnFromPlayer() {
    int column = 0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter a column");
    try {
      column = scanner.nextInt();
    } catch (InputMismatchException ImE) {
      System.out.println("That was not a valid column, please enter a number from 0 to 2");
      getColumnFromPlayer();
    }

    if (column >= 0 && column < NUMBER_OF_COLUMNS) {
      return column;
    } else {
      System.out.println("That was not a valid column, please enter a number from 0 to 2");
      getColumnFromPlayer();
    }
    return column;
  }

  /**
   * Indicates to the player that the position they entered is not open.
   */
  public void positionTaken() {
    System.out.println("Bummer, that position is already taken, choose again.");
  }

  /**
   * Prints the given string.
   *
   * @param string A string to be printed.
   */
  public void printJSON(String string) {
    System.out.printf("%n%n");
    System.out.println(string);
  }

  /**
   * Indicates to the player that the computer will play first.
   */
  public void computerFirst() {
    System.out.printf("%n");
    System.out.println("I'll go first.");
  }
}
