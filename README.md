# Isola Game
## Introduction
Isola is a 2 player game played on a 7x7 board with the following rules: 

1) Each of the two players has one piece.
2) The board has 7x7 positions which initially contain squares, except for the initial positions of the pieces.
3) A move consists of two subsequent actions:
   1) moving one's piece to a neighboring (horizontally, vertically, or diagonally) field that contains a square but not the opponent's piece,
   2) removing any square with no piece on it
4) If a player cannot move any more, he/she loses the game.

Info from [here](https://www.cs.umb.edu/~yunxu/isola/rules.html)
## Minimax
The AI implemented here uses the minimax algorithm, with an evaluation function that takes into account mobility of both players, distance from center, and the number of moves taken.

Read more about minimax [here](https://en.wikipedia.org/wiki/Minimax)

## Demo (2x speed)
![isola_game_github_demo](https://user-images.githubusercontent.com/37674516/82745455-83a92180-9d52-11ea-871d-d1b907658d4e.gif)

