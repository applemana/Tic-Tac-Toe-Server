/* This files responsibility is to provide the logic for the computer.
 * Credit: https://gist.github.com/MatthewSteel/3158579 
 */


#include <stdio.h>
#include "game.h"

/*
Input:         
  board:       The board that will be checked. 
Returns:       the board location where the player has the oppertunity to win.
Side Effects:  No side effects
Responsiblity: Identify all possible winning moves the player could have on 
               their next turn.
*/
int win(const int board[9]) {
  unsigned wins[8][3] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
  int i;
  for(i = 0; i < 8; ++i) {
    if(board[wins[i][0]] != 0 &&
      board[wins[i][0]] == board[wins[i][1]] &&
      board[wins[i][0]] == board[wins[i][2]]) {
      return board[wins[i][2]];
    }
  }
  return 0;
}

/*
Input:         
  board:       The board that will be checked.
  player:      The integer that represents the player. 
Returns:       A value indicating the best move possible.
Side Effects:  No side effects
Responsiblity: Identify the best move the computer could play.
*/
int try_moves(int board[9], int player) {
  int winner = win(board);
  if(winner != 0) {
    return winner*player;
  }

  int move = -1;
  int score = -2;
  int i;
  for(i = 0; i < 9; ++i) {
    if(board[i] == 0) {
      board[i] = player;
      int thisScore = -try_moves(board, player*-1);
      if(thisScore > score) {
        score = thisScore;
        move = i;
      }
      board[i] = 0;
    }
  }
  if(move == -1) {
    return 0;
  }
  return score;
}

/*
Input:         No input
Returns:       The location on the board in which the computer would like to 
               make their move.
Side Effects:  No side effects
Responsiblity: Identify the best move the computer could play.
*/
int computer_play() {
  int move = -1;
  int score = -2;
  int i;

  for(i = 0; i < 9; ++i) {
    if(board[i] == 0) {
      board[i] = 1;
      int tempScore = -try_moves(board, -1);
      board[i] = 0;
      if(tempScore > score) {
        score = tempScore;
        move = i;
      }
    }
  }
  return move;
}
