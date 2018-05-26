package Model.Connection;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import Model.Position.Position;

public interface IConnection {

  /**
   * Reads a JSON string from the given url.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   *
   * @return a JSONObject containing the JSON from the web page.
   * @throws IOException when the web page is empty.
   * @throws ParseException when separating a JSON string.
   */
  JSONObject readJSON(String url) throws IOException, ParseException;

  /**
   * Queries the server for the computers next move returns it as a Position
   *
   * @return Position containing the computers move.
   * @throws IOException when the web page is empty.
   * @throws ParseException when separating a JSON string.
   */
  Position receiveMove() throws IOException, ParseException;

  /**
   * Opens a connection with the server at url.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   * @throws IOException when the web page is empty.
   */
  void openConnection(String url) throws IOException;

  /**
   * Closes all open connections with the server.
   */
  void closeConnection();

  /**
   * Sends jsonObject to the server.
   * @param jsonObject The JSON that is being sent to the server.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   * @throws IOException when the web page is empty.
   */
  void sendJSON(JSONObject jsonObject, String url) throws IOException;
}
