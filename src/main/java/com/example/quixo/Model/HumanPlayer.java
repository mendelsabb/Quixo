package com.example.quixo.Model;

public class HumanPlayer implements Player {

    public void move(Position source, Position destination, int player, int[][] game) {

        int xSource = source.getX(), ySource = source.getY();
        int xDest = destination.getX(), yDest = destination.getY();

        // אם מיקומי המקור והיעד נמצאים על אותה עמודה, העבר את החלקים בצורה אנכית כדי למלא את הפער
        if (ySource == yDest) {
            // אם ליעד יש ID גבוהה יותר, העבר את החלקים ימינה
            if (xSource < xDest) {
                for (int i = xSource; i < 4; i++) {
                    game[i][ySource] = game[i + 1][ySource];
                }
            }
            // אם ליעד יש ID נמוך יותר, העבר את החלקים שמאלה
            if (xSource > xDest) {
                for (int i = xSource; i > 0; i--) {
                    game[i][ySource] = game[i - 1][ySource];
                }
            }
        }

        // אם מיקומי המקור והיעד נמצאים באותה שורה, העבר את החלקים אופקית כדי למלא את הפער
        if (xSource == xDest) {
            // אם ליעד יש ID גבוהה יותר, העבר את החלקים ימינה
            if (ySource < yDest) {
                for (int i = ySource; i < 4; i++) {
                    game[xSource][i] = game[xSource][i + 1];
                }
            }
            // אם ליעד יש ID נמוך יותר, העבר את החלקים שמאלה
            if (ySource > yDest) {
                for (int i = ySource; i > 0; i--) {
                    game[xSource][i] = game[xSource][i - 1];
                }
            }
        }

        // Place the player's piece at the destination position on the game board
        game[xDest][yDest] = player;
    }
}
