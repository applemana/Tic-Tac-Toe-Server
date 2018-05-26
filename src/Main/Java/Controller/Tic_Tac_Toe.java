package Controller;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Model.Cells.ComputerCell;
import Model.Cells.PlayerCell;
import Model.Connection.Connection;
import Model.Game.Game;
import Model.Position.Position;
import View.Console;

import static Model.Connection.Connection.urlComputer;
import static Model.Connection.Connection.urlGame;
import static Model.Game.Game.COMPUTERS_TURN;
import static Model.Game.Game.PLAYERS_TURN;

/**
 * Represents the top level methods for the game Tic-Tac-Toe.
 */
public class Tic_Tac_Toe {
  private Connection connection = new Connection();
  private static JSONObject jsonObject;
  private static JSONArray playerPositions = new JSONArray();
  private static JSONArray computerPositions = new JSONArray();
  private static int playerMoves = 0;
  private static int computerMoves = 0;

  /**
   * Creates a new instance of Tic_Tac_Toe.
   */
  public Tic_Tac_Toe() {
  }
  /**
   * The main method which initiates Tic-Tac-Toe.
   *
   * @param args the arguments passed in by the user from the command line.
   * @throws IOException when the user inputs an incorrect option.
   * @throws ParseException when separating a JSON string.
   */
  public static void main(String[] args) throws IOException, ParseException {
    Tic_Tac_Toe tic_tac_toe = new Tic_Tac_Toe();

    tic_tac_toe.startGame(args);
  }

  /**
   * Initiates the game and identifies who's turn it is first.
   *
   * @param args the arguments passed in by the user from the command line.
   * @throws IOException when the user inputs an incorrect option.
   */
  @SuppressWarnings("unchecked")
  private void startGame(String[] args) throws IOException, ParseException {
    Game game = new Game();
    Console display = new Console();
    String urlWelcome = "http://localhost:1234/welcome/";

    connection.openConnection(urlWelcome);
    JSONObject welcomeJSON = connection.readJSON(urlWelcome);
    display.printJSON(welcomeJSON.get("Welcome").toString());

    jsonObject = new JSONObject();
    jsonObject.put("Game", "Tic-Tac-Toe");

    if (game.getWhosTurn().equals(PLAYERS_TURN)) {
      connection.openConnection(urlGame);
      playersTurn(game, display);
    } else {
      display.computerFirst();
      connection.openConnection(urlComputer);
      connection.sendJSON(jsonObject, urlGame);
      computersTurn(game, display);
    }
  }

  /**
   * Gets input from the player to determine where they would like their move to be.
   *
   * @param game    the game that the player is affecting.
   * @param display the console that the commands and board will print to.
   * @throws IOException when the user inputs an incorrect option.
   */
  private void playersTurn(Game game, Console display) throws IOException, ParseException {
    game.getBoard().printBoard(display);

    int row = display.getRowFromPlayer();
    int column = display.getColumnFromPlayer();

    Position position = new Position(row, column);
    checkChoice(game, display, position);
    game.getBoard().printBoard(display);

    if (game.isWin()) {
      display.playerWon();
      connection.closeConnection();

    } else if (game.getBoard().isFull()) {
      display.tie();
    } else {
      game.setWhosTurn(COMPUTERS_TURN);
      addPositionToPlayerJSON(position);
      connection.sendJSON(jsonObject, urlGame);
      computersTurn(game, display);
    }
  }

  /**
   * Adds the players position to the JSON object.
   * @param position
   */
  @SuppressWarnings("unchecked")
  private void addPositionToPlayerJSON(Position position) {
    playerMoves++;
    JSONObject pos = new JSONObject();
    pos.put("Row", position.getRow());
    pos.put("Column", position.getColumn());

    playerPositions.add(pos);
    jsonObject.put("Player", playerPositions);
    jsonObject.put("Player Moves", playerMoves);
  }

  /**
   * Adds the computers position to the JSON object.
   * @param position
   */
  @SuppressWarnings("unchecked")
  private void addPositionToComputerJSON(Position position) {
    computerMoves++;
    JSONObject pos = new JSONObject();
    pos.put("Row", position.getRow());
    pos.put("Column", position.getColumn());

    computerPositions.add(pos);
    jsonObject.put("Computer", computerPositions);
    jsonObject.put("Computer Moves", computerMoves);
  }

  /**
   * Checks the position that was entered to ensue that it is an open cell.
   *
   * @param game     the current game the user is playing.
   * @param display  the console the game will be printed to.
   * @param position the position on the board that is being validated.
   * @throws IOException when the user inputs an incorrect option.
   */
  private void checkChoice(Game game, Console display, Position position)
      throws IOException, ParseException {

    if (game.getBoard().getCellAtPosition(position).isOpen()) {
      game.getBoard().setCellAtPosition(new PlayerCell(), position);
    } else {
      display.positionTaken();
      playersTurn(game, display);
    }
  }

  /**
   * Communicates with the server to get the computers turn.
   *
   * @param game    the game that the player is affecting.
   * @param display the console that the commands and board will print to.
   * @throws IOException when the user inputs an incorrect option.
   */
  private void computersTurn(Game game, Console display) throws IOException, ParseException {
    Position position = connection.receiveMove();
    game.getBoard().setCellAtPosition(new ComputerCell(), position);

    if (game.isWin()) {
      game.getBoard().printBoard(display);
      display.computerWon();
      connection.closeConnection();
    } else if (game.getBoard().isFull()) {
      game.getBoard().printBoard(display);
      display.tie();
      connection.closeConnection();
    } else {
      game.setWhosTurn(PLAYERS_TURN);
      addPositionToComputerJSON(position);
      playersTurn(game, display);
    }
  }
}
