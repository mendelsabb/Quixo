


package com.example.quixo.Model;


import java.util.ArrayList;
import java.util.Random;

public class PlayerAI {

    Integer playerTurn = 2 ;//כדי לזהות שזה הAI

    Random rd = new Random();
    public PlayerAI(Integer playerTurn) {
        this.playerTurn = playerTurn;
    }


    public ArrayList<Position> bestSearch(int[][] game){
        // מחזיר את המהלכים שניתן לשחק עבור הAI
        Board board = new Board();
        int[][] gameTemp = cloneGame(game); //clone the table
        ArrayList<Position> bestChoice = new ArrayList<>(2);//המהלכים הטובים ביותר
        int minVal = rd.nextInt();


        // ליצור עותק מערך
        board.setGame(cloneGame(game));

        ArrayList<Position> movables = board.getPlayableSources(playerTurn);//למצוא את העמדות הניתנות להפעלה עבור ה-AI
        for (Integer i = 0; i < movables.size(); i++) {
            // מאפס את הלוח עבור כל ריבוע שניתן לשחק ולהימנע משגיאות

            board.setGame(cloneGame(game));
            ArrayList<Position> destinations = board.getPlayableDestinations(5,movables.get(i));//מוצא את היעדים שניתן לשחק עבור כל עמדה ניתנת להזזה
            for (int j = 0; j < destinations.size(); j++ ) {
                move(movables.get(i), destinations.get(j), this.playerTurn, board.getGame());//מבצע מעבר מהמיקום הנייד הנוכחי ליעד הנוכחי
                    // קורא לחיפוש היוריסטי כדי לאחזר את הערך הטוב ביותר אם אנחנו מתכוונים לבצע את המהלך הזה(destination.
                int scoreRand = heuristicSearch(cloneGame(board.getGame()),2,this.playerTurn );

                //לקבוע אם המהלך הנוכחי טוב יותר מהמהלך הטוב ביותר הנוכחי, ולעדכן את bestChoice ArrayList במידת הצורך
                if (scoreRand>minVal || (i==0&&j==0)){
                    minVal = scoreRand;
                    bestChoice.add(0, movables.get(i));
                    bestChoice.add(1 ,destinations.get(j));
                    bestChoice.subList(2,bestChoice.size()).clear();
                }
            }
        }
        return bestChoice;
    }




    // מוצא את כל התיבות הניתנות להזזה ואת היעד של כל מהלך, ואז קרא לפונקציה זו.
    // משווה את התוצאה של כל המהלכים עם הציון הטוב ביותר ובוחר את הטוב ביותר בסוף.

/*
קודם כל זה יוצר שיבוט של הלוח הנוכחי כך שכל שינוי לא ישפיע על המשחק שלנו
שנית הוא מקבל את כל המהלכים הניתנים למשחק שנמצאים על הלוח ולאחר
 מכן לבדוק עד לעומק שמועבר כפרמטר בחיפוש ההיוריסטי קבל מספר אקראי ולפיו תבדוק מהי הבחירה הטובה ביותר עבור AI
 */

    //השינויים שאני הולך לעשות הוא עכשיו אני אקצה משקלים לכל ריבוע בהתאם לערכים האסטרטגיים שלהם ואעריך את המהלכים

    public int heuristicSearch(int[][] game, int depth, int currentplayer){
        // מחזיר מספר שלם המייצג את הערך היוריסטי של המהלך הטוב ביותר עבור השחקן הנוכחי

        Board board = new Board();
        // יוצור עותק של מערך המשחק כדי להימנע משינוי המערך המקורי
        int[][] gameTemp = cloneGame(game);

        board.setGame(cloneGame(game));
        ArrayList<Position> movables = board.getPlayableSources(currentplayer);// קבל את החלקים הניתנים להזזה עבור השחקן הנוכחי
        int myScore = getBoardScore(board.getGame(), currentplayer);//מקבל את הניקוד של השחקן הנוכחי
        // בודק אם החיפוש הגיע למגבלת העומק שצוינה או שהמשחק הסתיים
        if (depth < 1 || myScore == board.getGame().length ) {
            return myScore;//  החזר את התוצאה של השחקן הנוכחי

        }
        // אחרת המשך לחשב את הניקוד על ידי קריאה יוריסטית לחיפוש
        int scoreFinale = 0;
        for (Integer i = 0; i < movables.size(); i++) {
            //חוזר על כל המהלכים האפשריים עבור השחקן הנוכחי
            //מעביר את קוביית המקור ליעד, וקורא באופן רקורסיבי ל-heuristicSearch() עם מצב המשחק המעודכן ועם שחקן היריב
            board.setGame(cloneGame(game));
            ArrayList<Position> destinations = board.getPlayableDestinations(5,movables.get(i));//מקבל את היעדים שניתנים להפעלה
            // לאחר כל מעבר יעד מקבלים את לוח המהמלכים הפאשריים החדש
            // מחשב את הניקוד
            for (int j = 0; j < destinations.size(); j++ ){
                // בודק איזה שחקן עושה את התזוזה
                // כל מהלך חייב להתבצע על לוח חדש על מנת למנוע את בעיית ההתייחסות במערכים של Java.
                if (currentplayer==2) {
                    scoreFinale = rd.nextInt();
                    move(movables.get(i), destinations.get(j), 2, cloneGame(board.getGame()));//בצע את המהלך עבור השחקן הנוכחי
                    // קרא לפונקציית החיפוש באופן רקורסיבי עבור היריב, ועדכן את הניקוד הסופי
                    scoreFinale = Integer.max(scoreFinale, heuristicSearch(cloneGame(board.getGame()), depth - 1, myOpponent(currentplayer)));
                } else{

                    scoreFinale = rd.nextInt();
                    move(movables.get(i), destinations.get(j), 1, board.getGame());
                    //קרא לפונקציית החיפוש באופן רקורסיבי עבור היריב, ועדכן את התוצאה הסופית
                    scoreFinale = Integer.min(scoreFinale, heuristicSearch(board.getGame(), depth - 1, myOpponent(currentplayer)));
                }
            }
        }
        return scoreFinale;//החזר את הניקוד הסופי

    }




    //**************************************************************************//

    public int getBoardScore(int[][] Board, int player)  {
        //הפונקציה מחזירה את הציון המקסימלי מתוך ארבעת הציונים הללו, המייצג את הציון הכולל של השחקן על לוח המשחק.
        int diagScore = getDiagScore(Board, player);
        int diagScoreOppose = getInvDiagScore(Board, player);
        int rowScore = getMaxRowScore(Board, player);
        int colsScore = getMaxColScore(Board, player);


        int max = Integer.max(diagScore,diagScoreOppose);
        max = Integer.max(max,rowScore);
        max = Integer.max(max,colsScore);
        return max;
    }


    public int getRowScore(ArrayList<Integer> line, int player )  {
        //חישוב ניקוד שורה
        int score = rd.nextInt();
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i) == player) {
                if (score==Integer.MIN_VALUE) score=1;
                else score++;
            }
        }

        return score;
    }

    public int getMaxRowScore(int[][] Board, int player)  {
        //
        int size = Board.length;

        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();

            //
            for(int j=0; j< Board[i].length; j++){
                row.add(Board[i][j]);

            }

            scores.add( getRowScore(row, player));
        }

        return getMaxInArray(scores);
    }

    public int getMaxColScore(int[][] Board, int player)  {
        int size = Board.length;
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> cols = new ArrayList<Integer>();
            //
            for(int j=0; j< Board.length; j++){
                cols.add(Board[j][i]);
            }
            scores.add( getRowScore(cols, player));
        }

        return getMaxInArray(scores);
    }

    public Integer myOpponent(Integer playerTurn){
        if (playerTurn==1)
            return 2;
        else
            return 1;
    }
    public int getDiagScore(int[][] Board, int player)  {
        ArrayList<Integer> diagonal = new ArrayList<>() ;
        int size = Board.length;
        for (int i = 0; i < size; i++ ){
            diagonal.add(Board[i][i]);
        }
        return getRowScore(diagonal, player);
    }

    public int getInvDiagScore(int[][] Board, int player) {
        ArrayList<Integer> diagonalInverse = new ArrayList<>() ;
        int size = Board.length;
        for (int i = 0; i < size; i++ ){
            diagonalInverse.add(Board[i][size-1-i]);
        }
        return getRowScore(diagonalInverse, player);
    }

    public int getMaxInArray(ArrayList<Integer> scoreArray)  {
        int max = 0;
        for(int i = 0; i < scoreArray.size(); i++) {
            if (scoreArray.get(i) > max) {
                max = scoreArray.get(i);
            }
        }
        return max;
    }



    public void move(Position A_source, Position B_Destination, int player, int [][] game) {

        int XSource = A_source.getX(), YSource = A_source.getY();
        int XDest = B_Destination.getX(), YDest = B_Destination.getY();
        /* אם הם נמצאים באותה עמודה, הדבר שיש לשנות הוא הקו
           אבל יש צורך גם להזיז את כל החלקים האחרים לפי 2 מקרים */
        if (YSource == YDest) {

            //אם ליעד יש ID גדול יותר
            // אנחנו עוברים ימינה (מגדילים)
            if (XSource < XDest) {
                for (int i = XSource; i < 4; i++) {

                    game[i][YSource] = game[i + 1][YSource];

                }
            }
            // אם ליעד יש ID קטן יותר
            // אנחנו עוברים שמאלה (ירידה)
            if (XSource > XDest) {
                for (int i = XSource; i > 0; i--) {
                    game[i][YSource] = game[i - 1][YSource];

                }
            }

        }
        /* אם הם נמצאים על אותה שורה, הדבר שיש לשנות הוא העמודה
         * אבל יש צורך גם להזיז את כל החלקים האחרים לפי 2 מקרים */
        if (XSource == XDest) {
            // אם ליעד יש ID גדול יותר
            // אנחנו עוברים ימינה (מגדילים)
            if (YSource < YDest) {
                for (int i = YSource; i < 4; i++) {

                    game[XSource][i]= game[XSource][i + 1];

                }
            }
            // אם ליעד יש ID קטן יותר
            // אנחנו עוברים שמאלה (ירידה)
            // אנחנו עוברים שמאלה (ירידה)
            if (YSource > YDest) {
                for (int i = YSource; i > 0; i--) {
                    game[XSource][i]= game[XSource][i - 1];

                }
            }
        }


        game[XDest][YDest] = player;

    }


    public void printGame(int[][] game){

        for (int i =0;i<5;i++){
            for(int j =0;j<5;j++){
                String val="?";
                if(game[i][j]==1){
                    val="O";
                }else if(game[i][j]==2){
                    val="X";
                }
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    public int [][] cloneGame(int[][] game){
        if(game == null) return null;

        int[][] result = new int[game.length][];
        for(int i=0; i< game.length; i++){
            result[i] = game[i].clone();
        }
        return result;
    }
}







