/*This file is responsable for communicating with the client and creating the 
game board after each get.*/
#include <stdio.h>
#include <ulfius.h>
#include <unistd.h>
#include <string.h>
#include "game.h"

#define PORT 1234
int shutdown_now = 0;
int move = 4;

/*
Input:         No input 
Returns:       Nothing returned
Side Effects:  Sets all values of the board = 0.
Responsiblity: Initilize the board.
*/
void initialize_board() {
  srand(time(NULL)); 
    
  for (int i=0; i<9; i++) {
    board[i] = 0; 
  }
}

/*
Input:         
  row:         The row of the board.
  column:      The column of the board.
  i:           The value to be set at the position.
Returns:       Nothing returned.
Side Effects:  Sets the value on the board = i.
Responsiblity: Set the value on the board = i,
*/
void set_cell_on_board(int row, int column, int i) {
  board[(row * 3) + column] = i;
}

/*
Input:         
  json_array:  A json array containing a position.
  c:           A char indicating which players turn is being placed. 
Returns:       Output (string copy of the input).
Side Effects:  Places the move on the board.
Responsiblity: Place the position in the json arry on the board in the proper 
               location.
*/
void place_moves_on_board(json_t *json_array, char c) {
  int size = json_array_size(json_array);
  int row, column;

  json_auto_t * position;
  json_auto_t * col;
  json_auto_t * r;

  for (int i = 0; i < size; i++) {
    position = json_array_get(json_array, i);

    col =  json_object_get(position, "Column");
    column = (int) json_number_value(col);

    r = json_object_get(position, "Row");
    row = (int) json_number_value(r);

    set_cell_on_board(row, column, c);

  }
}

/*
Input:         
  request:     The request that was posted by the client.
  response:    The responce that the server will make. 
  user_data:   Additional data that the function can use to run.
Returns:       An int indicating to continue service.
Side Effects:  A JSON object is pushed or pulled from a web address.
Responsiblity: Push a welcome message to the client indicating that the 
               connection was successful.
*/
int callback_start_game(const struct _u_request * request, struct _u_response * response, void * user_data) {
  json_auto_t * json_body = NULL;
  char greeting[50];

  sprintf(greeting, "Oh another challanger... Welcome");

  json_auto_t * json_greeting_message = json_string(greeting);

  json_body = json_object();
  json_object_set(json_body, "Welcome", json_greeting_message);

  ulfius_set_json_body_response(response, 200, json_body);

  return U_CALLBACK_CONTINUE;
}

/*
Input:         
  request:     The request that was posted by the client.
  response:    The responce that the server will make. 
  user_data:   Additional data that the function can use to run.
Returns:       An int indicating to continue service.
Side Effects:  A JSON object is pushed or pulled from a web address.
Responsiblity: Pull a JSON object that indicates all of the players and all of 
               the servers previous moves and recreates the board according to
               the positions in the JSON.
*/
int callback_get_turn(const struct _u_request * request, struct _u_response * response, void * user_data) {
  json_auto_t * json_game = ulfius_get_json_body_request(request, NULL);
  
  json_auto_t * computers_moves;
  json_auto_t * num_computer_moves;
  json_auto_t * players_moves;
  json_auto_t * num_player_moves;
  json_auto_t * whos_turn;

  initialize_board();

  whos_turn = json_object_get(json_game, "Who's Turn");
  double turn = json_number_value(whos_turn);
  
  num_player_moves = json_object_get(json_game, "Player Moves");
  players_moves = json_object_get(json_game, "Player");

  num_computer_moves = json_object_get(json_game, "Computer Moves");
  computers_moves = json_object_get(json_game, "Computer");

  if (players_moves != NULL) {
    place_moves_on_board(players_moves, -1);
  } 

  if (computers_moves != NULL) {
    place_moves_on_board(computers_moves, 1);
  }

  move = computer_play();

  return U_CALLBACK_CONTINUE;
}

/*
Input:         
  request:     The request that was posted by the client.
  response:    The responce that the server will make. 
  user_data:   Additional data that the function can use to run.
Returns:       An int indicating to continue service.
Side Effects:  A JSON object is pushed or pulled from a web address.
Responsiblity: Push a JSON object to the web that indicates the computers next 
               move.
*/
int callback_play_turn(const struct _u_request * request, struct _u_response * response, void * user_data) {
  char greeting[50];

  json_auto_t * row;
  json_auto_t * column;

  sprintf(greeting, "Finally, My turn!!");
  json_auto_t * message = json_string(greeting);

  if (move < 3) {
    row = json_integer(0);
    column = json_integer(move);
  } else if (move >= 3 && move < 6) {
    row = json_integer(1);
    column = json_integer(move - 3);
  } else if (move >= 6) {
    row = json_integer(2);
    column = json_integer(move - 6);
  }

  json_auto_t * json_body = NULL;
  json_body = json_object();
  json_object_set(json_body, "Taunting", message);
  json_object_set(json_body, "Column", column);
  json_object_set(json_body, "Row", row);

  ulfius_set_json_body_response(response, 200, json_body);

  return U_CALLBACK_CONTINUE;
}


/*
Input:         No inputs
Returns:       Nothing returned.
Side Effects:  No side effects.
Responsiblity: A port is opened between the client and the server allowing for 
               RESTful communication between the two until the server is shut 
               down.
*/
int main(void) {
  struct _u_instance instance;

  if (ulfius_init_instance(&instance, PORT, NULL, NULL) != U_OK) {
    fprintf(stderr, "Error ulfius_init_instance, abort\n");
    return(1);
  }

  ulfius_add_endpoint_by_val(&instance, "GET", "/welcome", NULL, 0, &callback_start_game, NULL);
  ulfius_add_endpoint_by_val(&instance, "*", "/computer", NULL, 1, &callback_play_turn, NULL);
  ulfius_add_endpoint_by_val(&instance, "*", "/game", NULL, 0, &callback_get_turn, NULL);

  if (ulfius_start_framework(&instance) == U_OK) {
    printf("\nPress the enter key to exit the application\n");
    getchar();
  } else {
    fprintf(stderr, "Error starting framework\n");
  }

  ulfius_stop_framework(&instance);
  ulfius_clean_instance(&instance);

  return 0;
}
