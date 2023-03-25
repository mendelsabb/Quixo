package com.example.quixo.Controller;
import com.example.quixo.Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller  {
    //Declaration
    /****************************************/
    @FXML
    private Button bt00;
    @FXML
    private Button bt01;
    @FXML
    private Button bt02;
    @FXML
    private Button bt03;
    @FXML
    private Button bt04;
    @FXML
    private Button bt10;
    @FXML
    private Button bt11;
    @FXML
    private Button bt12;
    @FXML
    private Button bt13;
    @FXML
    private Button bt14;
    @FXML
    private Button bt20;
    @FXML
    private Button bt21;
    @FXML
    private Button bt22;
    @FXML
    private Button bt23;
    @FXML
    private Button bt24;
    @FXML
    private Button bt30;
    @FXML
    private Button bt31;
    @FXML
    private Button bt32;
    @FXML
    private Button bt33;
    @FXML
    private Button bt34;
    @FXML
    private Button bt40;
    @FXML
    private Button bt41;
    @FXML
    private Button bt42;
    @FXML
    private Button bt43;
    @FXML
    private Button bt44;
    @FXML
    private GridPane GD;
    @FXML
    private Label idCurrentPlayer;
    @FXML
    private Label idShowWinner;
    @FXML
    private Label WinnerLabel;

    /*****************************************/
    Player human = new HumanPlayer();
    public Board p = new Board();
    public static int playerTurn = 1;
    public boolean optionSelect =true;//first click

    public ArrayList<Position> myArrayDest1 = new ArrayList<Position>();
    // מיקומי הכפתורים שהמשתמש יכול לבחור
    public ArrayList<Position> myArraySource1= p.getPlayableSources(playerTurn);
    // אתחול המהלכים הניתנים לשחק עבור השחקן הראשון

    //the positions of the pieces played
    public Position selectdestination;
    public Position selectSource;
    ArrayList<Button> allButtons;
    ArrayList<Button> sideButtons;

   /************************************************************/

    @FXML
    void RestartGame(ActionEvent event) {
        //Restart - "פונקציה לאיפוס הלוח "התחלת משחק חדש
        allButtons = new ArrayList<>(Arrays.asList(bt44,bt43,bt42,bt41,bt40,bt34,bt33,bt32,bt31,bt30,bt24,bt23,bt22,bt21,bt20,bt10,bt11,bt12,bt13,bt14,bt04,bt03,bt02,bt01,bt00));
        sideButtons = new ArrayList<>(Arrays.asList(bt44,bt43,bt42,bt41,bt40,bt34,bt30,bt24,bt20,bt10,bt14,bt04,bt03,bt02,bt01,bt00));
        p = new Board();
                /*
                 int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                };*/
        allButtons.forEach(button ->{
           button.setText("");
        });
        playerTurn = 1;
        sideButtons.forEach(button ->{ if(button.isDisabled()==true){
            button.setDisable(false);} });
        idShowWinner.setText("");
        idCurrentPlayer.setText("Human Turn");
        optionSelect = true;
        myArrayDest1 = new ArrayList<Position>();
        myArraySource1= p.getPlayableSources(playerTurn);
        UpdateDisplay();
    }


    //This Function updates the boxes and changes in the Board
    public void UpdateDisplay(){
        if (p.game[0][0]== 2){bt00.setText("X");}else if (p.game[0][0]== 1){bt00.setText("O"); } else {bt00.setText(""); }
        if (p.game[0][1]== 2){bt10.setText("X");}else if (p.game[0][1]== 1) {bt10.setText("O");} else {bt10.setText("");}
        if (p.game[0][2]== 2){bt20.setText("X");}else if (p.game[0][2]== 1) bt20.setText("O"); else {bt20.setText("");}
        if (p.game[0][3]== 2){bt30.setText("X");}else if (p.game[0][3]== 1) bt30.setText("O"); else {bt30.setText("");}
        if (p.game[0][4]== 2){bt40.setText("X");}else if (p.game[0][4]== 1) bt40.setText("O"); else {bt40.setText("");}
        if (p.game[1][0]== 2){bt01.setText("X");}else if (p.game[1][0]== 1) bt01.setText("O");else {bt01.setText("");}
        if (p.game[1][1]== 2){bt11.setText("X");}else if (p.game[1][1]== 1) bt11.setText("O");else {bt11.setText("");}
        if (p.game[1][2]== 2){bt21.setText("X");}else if (p.game[1][2]== 1) bt21.setText("O");else {bt21.setText("");}
        if (p.game[1][3]== 2){bt31.setText("X");}else if (p.game[1][3]== 1) bt31.setText("O");else {bt31.setText("");}
        if (p.game[1][4]== 2){bt41.setText("X");}else if (p.game[1][4]== 1) bt41.setText("O");else {bt41.setText("");}
        if (p.game[2][0]== 2){bt02.setText("X");}else if (p.game[2][0]== 1) bt02.setText("O");else {bt02.setText("");}
        if (p.game[2][1]== 2){bt12.setText("X");}else if (p.game[2][1]== 1) bt12.setText("O"); else {bt12.setText("");}
        if (p.game[2][2]== 2){bt22.setText("X");}else if (p.game[2][2]== 1) bt22.setText("O");else {bt22.setText("");}
        if (p.game[2][3]== 2){bt32.setText("X");}else if (p.game[2][3]== 1) bt32.setText("O");else {bt32.setText("");}
        if (p.game[2][4]== 2){bt42.setText("X");}else if (p.game[2][4]== 1) bt42.setText("O");else {bt42.setText("");}
        if (p.game[3][0]== 2){bt03.setText("X");}else if (p.game[3][0]== 1) bt03.setText("O");else {bt03.setText("");}
        if (p.game[3][1]== 2){bt13.setText("X");}else if (p.game[3][1]== 1) bt13.setText("O"); else {bt13.setText("");}
        if (p.game[3][2]== 2){bt23.setText("X");}else if (p.game[3][2]== 1) bt23.setText("O");else {bt23.setText("");}
        if (p.game[3][3]== 2){bt33.setText("X");}else if (p.game[3][3]== 1) bt33.setText("O");else {bt33.setText("");}
        if (p.game[3][4]== 2){bt43.setText("X");}else if (p.game[3][4]== 1) bt43.setText("O");else {bt43.setText("");}
        if (p.game[4][0]== 2){bt04.setText("X");}else if (p.game[4][0]== 1) bt04.setText("O");else {bt04.setText("");}
        if (p.game[4][1]== 2){bt14.setText("X");}else if (p.game[4][1]== 1) bt14.setText("O");else {bt14.setText("");}
        if (p.game[4][2]== 2){bt24.setText("X");}else if (p.game[4][2]== 1) bt24.setText("O");else {bt24.setText("");}
        if (p.game[4][3]== 2){bt34.setText("X");}else if (p.game[4][3]== 1) bt34.setText("O");else {bt34.setText("");}
        if (p.game[4][4]== 2){bt44.setText("X");}else if (p.game[4][4]== 1) bt44.setText("O");else {bt44.setText("");}
    }


    // פונקציה זו מייצגת את המהלך הראשון של השחקן
    public void select(Integer rowIndex, Integer colIndex){
    //מציג את הכפתורים שהמשתמש יכול לבחור..
    selectSource = new Position(colIndex, rowIndex);

    if (myArraySource1.stream().filter((position) -> position.x== selectSource.x && position.y== selectSource.y).anyMatch(position -> true) ){
        //מצב המערך (לחצנים) תלוי ברשימה זו myArrayDist (לאחר בחירת כפתור)
        myArrayDest1 = p.getPlayableDestinations(5, selectSource); //מערך של כל האופציות האפשריות אחרי הלחיצה
        // אני מבטל את כל הכפתורים

        disableAllNode();
        //אני מפעיל את כל הכפתורים שיכולים להילחץ
        EnableButtons(myArrayDest1);
        //  empty  myArraySource list - מרוקן רשימת יעדים
        myArraySource1.clear();

        optionSelect =false;
        //מעדכן לפי השינויים
        UpdateDisplay();
        p.printBoard();
        }
    }


    //פונקציית זו מתאימה ללחיצה השנייה של השחקן

    public void displace(Integer rowIndex, Integer colIndex){
        //עמדות שאליהן השחקן יכול להעביר את המהלך שלו
        selectdestination = new Position(colIndex, rowIndex);

        if (myArrayDest1.stream().filter((position) -> position.x== selectdestination.x && position.y== selectdestination.y).anyMatch(position -> true)){
            //עושה תהזזה
            human.move(selectSource, selectdestination,playerTurn,p.game);
            UpdateDisplay();
            if(p.isSolved()==-10){
                idShowWinner.setText("AI Wins!");



                //disable All - מאפסים הכל כי היה נצחון
                disableAllNode();
                UpdateDisplay();
            }else if(p.isSolved()==10){
                idShowWinner.setText("Human wins! Congratulations");
                //disable All - מאפסים הכל כי היה נצחון
                disableAllNode();
                UpdateDisplay();
            }else{

                playerTurn = playerTurn==1 ? 2 : 1;
                myArrayDest1.clear();
                myArraySource1 = p.getPlayableSources(playerTurn);
                disableAllNode();
                EnableButtons(myArraySource1);

                optionSelect = true;
                UpdateDisplay();
                p.printBoard();
            }
        }
    }


    private static Controller cntrl = new Controller();

    public static Controller getInstance()
    {   return cntrl;
    }


    //פונקציה זו מתאימה ללחיצה שנעשתה על ידי המשתמש בלוח
    @FXML
    void play(ActionEvent e) throws InterruptedException {
//ברגע שיש אינטרקציה("לחיצה") על מקש שהוא enabled או כאשר הגיע תור הAI
        idCurrentPlayer.setText("Human Turn");//מציב בlabel טקסט
        Node source = (Node) e.getSource();//שמירת הנתונים של הכפתור שנבחר
        Integer rowIndex = GD.getRowIndex(source)==null? 0: GD.getRowIndex(source);//שורה
        Integer colIndex = GD.getColumnIndex(source)==null?0: GD.getColumnIndex(source);//עמודה
        System.out.println(rowIndex+""+colIndex);
        // AI - אם זה הלחיצה הראשונה אז בחר תיבה, אחרת הזז והעביר את התור לשחקן השני

        if(playerTurn==1) {
            idCurrentPlayer.setText("Human Turn");
            if (optionSelect) {//אם זאת הלחיצה הראשונה, אנחנו נראה את האופציות האפשריות שיש לנו להכניס את הריבוע שבחרנו


                select(colIndex, rowIndex);//יראה לנו את האופציות האפשריות שיש לנו להכניס את הריבוע שבחרנו

            } else {
                displace(colIndex, rowIndex);//בחירת אחת מהאופיצות האפשריות
                Thread.sleep(500);
            }

            UpdateDisplay();

        }
        // לאחר שהשחקן שיחק (ה-AI חייב גם לשחק ולשם כך צריך להמשיך את הביצוע ישירות)

        if(playerTurn==2){
            idCurrentPlayer.setText("AI Turn");
            UpdateDisplay();
            PlayerAI playerIa = new PlayerAI(2);
            int [][]newGame = p.getGame();//מקבל איך הלוח נראה
            Board boardTemp = new Board(newGame.clone());//יוצר לוח זמני כדי לטפל בו בהכרעת מצבים


            Thread.sleep(1000);
                ArrayList<Position> randChoice = playerIa.bestSearch(boardTemp.getGame());//חיפוש המהלך הכי טוב בשביל הAI על הלוח הזמני

            if (optionSelect) {
                // תזוזה שמופשעת מהניקוד

                select(randChoice.get(0).y, randChoice.get(0).x);
                displace(randChoice.get(1).y, randChoice.get(1).x);
                UpdateDisplay();

                idCurrentPlayer.setText("Human Turn");
            }
        }
    }


    // disables all buttons
    public void disableAllNode () {
        ObservableList<Node> allButtons = GD.getChildren();

        for (Node node : allButtons) {
            node.setDisable(true);
        }
    }



    // active all buttons
    public void EnableButtons(ArrayList<Position> ButtonsPosition){

        ObservableList<Node> allButtons = GD.getChildren();

        for (Node node : allButtons) {
            for (Position po : ButtonsPosition) {
                Integer rowIndex = GD.getRowIndex(node)==null? 0: GD.getRowIndex(node);
                Integer colIndex = GD.getColumnIndex(node)==null?0: GD.getColumnIndex(node);
                if (rowIndex== po.getX() && colIndex== po.getY())
                    node.setDisable(false);
            }
        }
    }

    @FXML
    void initialize(){
        // all buttons are disabled first

        disableAllNode();
        // מפעיל את הכפתורים הניתנים להפעלה עבור השחקן
        EnableButtons(myArraySource1);


    }
}
