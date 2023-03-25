package com.example.quixo.Model;

import java.util.ArrayList;

public class Board {

    public int[][] game = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };// מטריצה של הלוח

    public Board() {
    }

    public Board(int[][] game) {
        this.game = game;
    }

    public static boolean isFirstRow(int i) {
        return i == 0;
    }

    public static boolean isLastRow(int i) {
        return i == 4;
    }

    public void setGame(int[][] game) {
        this.game = game;
    }

    public int[][] getGame() {
        return game;
    }

    public ArrayList<Position> getPlayableDestinations(int boardSize, Position point) {
        ArrayList<Position> playableDestinations = new ArrayList<Position>();

        if (isFirstRow(point.getX()) || isLastRow(point.getX())) {
            if (isFirstRow(point.getY()) || isLastRow(point.getY())) {
                playableDestinations.add(new Position(point.getX(), 4 - point.getY()));
                playableDestinations.add(new Position(4 - point.getX(), point.getY()));
            } else {
                playableDestinations.add(new Position(4 - point.getX(), point.getY()));
                playableDestinations.add(new Position(point.getX(), 0));
                playableDestinations.add(new Position(point.getX(), 4));
            }
        } else {
            playableDestinations.add(new Position(4, point.getY()));
            playableDestinations.add(new Position(0, point.getY()));
            playableDestinations.add(new Position(point.getX(), 4 - point.getY()));
        }

        return playableDestinations;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String val = "?";
                if (game[i][j] == 1) {
                    val = "O";
                } else if (game[i][j] == 2) {
                    val = "X";
                }
                //System.out.print(val + " ");
            }

        }

    }

    public ArrayList<Position> getPlayableSources(int player) {
        ArrayList<Position> playableSources = new ArrayList<Position>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 0) || (i == 4) || (j == 0) || (j == 4)) {
                    if ((game[i][j] == 0) || (game[i][j] == player)) {
                        playableSources.add(new Position(i, j));
                    }
                }
            }
        }
        return playableSources;
    }

    // בדיקת נצחון
    public int isSolved() {
        // Check rows - עובר על כל השורות
        for (int row = 0; row < 5; row++) {
            if (game[row][0] == game[row][1] &&
                    game[row][1] == game[row][2] &&
                    game[row][2] == game[row][3] &&
                    game[row][3] == game[row][4]) {
                if (game[row][0] == 1)
                    return 10;      //this.winnerPlayer = 'I';
                else if (game[row][0] == 2)
                    return -10;
            }

        }


        for (int col = 0; col < 5; col++) {
            if (game[0][col] == game[1][col] &&
                    game[1][col] == game[2][col] &&
                    game[2][col] == game[3][col] &&
                    game[3][col] == game[4][col]) {
                if (game[0][col] == 1)
                    return 10;      //this.winnerPlayer = 'I';
                else if (game[0][col] == 2)
                    return -10;
            }

        }


        //Check diagonals - עובר על כל העמודות
        if(game[0][0]==game[1][1]&&game[1][1]==game[2][2]&&game[2][2]==game[3][3]&&game[3][3]==game[4][4])
            {
                if (game[0][0] == 1) {
                    //this.winnerPlayer = 'H';
                    return -10;
                } else if (game[0][0] == 2) {
                    //this.winnerPlayer = 'I';
                    return 10;
                }
            }

        if(game[0][4]==game[1][3]&&game[1][3]==game[2][2]&&game[2][2]==game[3][1]&&game[3][1]==game[4][0])

            {
                if (game[0][4] == 1) {
                    //this.winnerPlayer = 'H';
                    return -10;
                } else if (game[0][4] == 2) {
                    //this.winnerPlayer = 'I';
                    return 10;
                }
            }

        //בודק אם אין זוכים "תיקו"
        for(
            int row = 0;
            row< 5;row++)

            {
                for (int column = 0; column < 5; column++) {
                    if (game[row][column] == 0) {
                        return 0;
                    }
                }
            }

        //It's a draw
        return 0;
}
}