package Model.Connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import Model.Position.Position;

/**
 * Represent the methods used to connect to the server.
 */
public class Connection {
  public static String urlGame = "http://localhost:1234/game";
  public static String urlComputer = "http://localhost:1234/computer";
  private static CloseableHttpClient httpClient;
  private static InputStream inputStream;

  /**
   * Creates a new instance of a connection.
   */
  public Connection() {

  }

  /**
   * Reads a JSON string from the given url.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   *
   * @return a JSONObject containing the JSON from the web page.
   * @throws IOException when the web page is empty.
   * @throws ParseException when separating a JSON string.
   */
  public JSONObject readJSON(String url) throws IOException, ParseException {
    String jsonString;
    inputStream = new URL(url).openStream();

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    jsonString = readAll(reader);

    JSONParser parser = new JSONParser();
    return (JSONObject) parser.parse(jsonString);
  }

  /**
   * Converts the content from reader into a string.
   * @param reader the JSON from the web page
   *
   * @return reader as a string value.
   * @throws IOException when the web page is empty.
   */
  private String readAll(Reader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = reader.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  /**
   * Queries the server for the computers next move returns it as a Position
   *
   * @return Position containing the computers move.
   * @throws IOException when the web page is empty.
   * @throws ParseException when separating a JSON string.
   */
  public Position receiveMove() throws IOException, ParseException {
    JSONObject computersTurn = readJSON(urlComputer);

    int row = ((Long) computersTurn.get("Row")).intValue();
    int column = ((Long) computersTurn.get("Column")).intValue();

    if (computersTurn.get("Row") != null) {
      return new Position(row, column);
    } else {
      return new Position(1,1);
    }
  }

  /**
   * Opens a connection with the server at url.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   * @throws IOException when the web page is empty.
   */
  public void openConnection(String url) throws IOException {
    httpClient = HttpClientBuilder.create().build();
    inputStream = new URL(url).openStream();
  }

  /**
   * Closes all open connections with the server.
   */
  public void closeConnection() {
    try {
      inputStream.close();
      httpClient.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends jsonObject to the server.
   * @param jsonObject The JSON that is being sent to the server.
   * @param url the Uniform Resource Locator of the web page the JSON string is located on.
   * @throws IOException when the web page is empty.
   */
  public void sendJSON(JSONObject jsonObject, String url) throws IOException {
    HttpPost httpPost = new HttpPost(url);
    String json = jsonObject.toJSONString();
    StringEntity post = new StringEntity(json);
    httpPost.addHeader("Content-type", "application/json");
    httpPost.setEntity(post);
    try {
      HttpResponse httpResponse = httpClient.execute(httpPost);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
