/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2015;

import java.net.URL;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

/**
 *
 * @author Shohag
 */
class SceneCreater {

    private Scene basicScene;
    private HBox basicHboxTop, basicHboxDown;
    private Label number = new Label("0");
    private VBox basicVboxLeft, basicVboxRight;
    private GridPane basicGridPane, rightBoxGrid;
    private BorderPane basicBorderPane;
    private Button anotherPuzzle = new Button("Try Another");
    private Button showButton = new Button("Show");

    private ImageView[] showLife = new ImageView[4];
    private Image life = new Image("project/pkg2015/res/easy/life.png", 60, 60, false, false);
    private Image cross = new Image("project/pkg2015/res/easy/none.png", 60, 60, false, false);
     private int showCount = 2;

    private ImageView[][] puzzleCell = new ImageView[6][6];
    private Image[][] puzzleImage = new Image[6][6];

    private Label gameName = new Label();
    private Label levelComplete = new Label();

    private int[][] gridValue = new int[6][6];
    private Random randV = new Random();
    private int[] randVArr = new int[26];
    private int indexX, indexY, moveX, moveY;
    private int clickCounter=0;

    private ImageView showPic = new ImageView();
    private ShowPuzzlePic showPuzzlePic = new ShowPuzzlePic();
    private Media clk, succes;
    private MediaPlayer pclk,psucces;
    private URL uclk, usucces;
    private int startTime, endTime, elapsedTime, score;
    private VBox afterComplete = new VBox();
    private Label Tscore = new Label();


    SceneCreater(){

        //Upper Label. It's the name of the game.
                basicHboxTop = new HBox();
                gameName.setFont(new Font(20));
                gameName.getStyleClass().add("my_Label");
                basicHboxTop.getStyleClass().add("my_Hbox");
                basicHboxTop.getChildren().add(gameName);

            //After copletion of  level
                afterComplete.setPadding(new Insets(20));
                afterComplete.setSpacing(5);


        //Media Loader for clicked event
              uclk = getClass().getResource("res/audio/clk.mp3");
             clk = new Media(uclk.toString());
             pclk = new MediaPlayer(clk);

             usucces = getClass().getResource("res/audio/succes.mp3");
             succes = new Media(usucces.toString());
             psucces = new MediaPlayer(succes);

        //Clicked counter
                basicHboxDown = new HBox();
                Label clicked = new Label("Clicked = ");
                clicked.setFont(new Font(20));

                number.setFont(new Font(20));
                basicHboxDown.getChildren().addAll(clicked, number);
                basicHboxDown.getStyleClass().add("my_Hbox");


        //Left side Level box
                basicVboxLeft = new VBox();
                Label level = new Label("Level");

                ChoiceBox<String> levelName = new ChoiceBox<>();
                levelName.getItems().addAll("Easy", "Medium","Hard");
                levelName.setValue("Easy");
                levelName.getSelectionModel().selectedItemProperty().addListener((v, OldV, newV)->{
                   if(newV.equalsIgnoreCase("Easy")){

                        showCount=2;

                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                     clickCounter=0;
                     number.setText(Integer.toString(clickCounter));
                     ForEasy();
                   }
                   else if(newV.equalsIgnoreCase("Medium")){

                       showCount=2;
                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                      clickCounter=0;
                     number.setText(Integer.toString(clickCounter));

                       ForMedium();

                   }

                   else if(newV.equalsIgnoreCase("Hard")){

                       showCount=2;
                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                      clickCounter=0;
                     number.setText(Integer.toString(clickCounter));

                       ForHard();
                   }
                });


                basicVboxLeft.getStyleClass().add("my_Vbox");
                basicVboxLeft.getChildren().addAll(level, levelName);


         //Right Side Help Box
                basicVboxRight = new VBox();
                rightBoxGrid = new GridPane();
                Label showLab = new Label("Remaining Show");
                showLab.setFont(new Font(20));

                try{
                 showLife[0] = new ImageView(life);
                 showLife[1] = new ImageView(life);
                 showLife[2] = new ImageView(life);
                }catch(Exception exception){};

                rightBoxGrid.setVgap(10);
                rightBoxGrid.setHgap(5);
                for(int x=0; x<3; x++)
                    rightBoxGrid.add(showLife[x], x, 0);

                basicVboxRight.getStyleClass().add("my_Vbox");
                basicVboxRight.getChildren().addAll(showLab,rightBoxGrid, showButton, anotherPuzzle);


        //Showed Scene
                basicBorderPane = new BorderPane();
                basicBorderPane.setTop(basicHboxTop);
                basicBorderPane.setBottom(basicHboxDown);
                basicBorderPane.setLeft(basicVboxLeft);
                basicBorderPane.setRight(basicVboxRight);


       basicScene = new Scene(basicBorderPane);
       basicScene.getStylesheets().add("project/pkg2015/myStyle.css");

    }


    //Create Scene for different levels
    Scene CreateScene(int i) {

        if(i==9)
        {
             ForEasy();
             return basicScene;
        }
        else if(i==16){
             ForMedium();
             return basicScene;
        }
        else {
              ForHard();
              return basicScene;
        }

    }





    //Scene for Easy Level
            private void ForEasy() {
                   startTime = (int) System.currentTimeMillis()/1000;

                int i, j, a, b, imageNumber=1, rand, temp;
                basicGridPane = new GridPane();
                basicGridPane.getStyleClass().add("my_Pane");

                //Change The current Level Name
                gameName.setText("Easy Puzzle");

                 //Show Button

                showButton.setOnAction(e->{
                  if(showCount>=0)
                  {
                      showLife[showCount].setImage(cross);
                      showCount--;
                      showPic.setImage(new Image("project/pkg2015/res/easy/pic.jpg",600,500, false, false));
                      showPuzzlePic.ShowPic(showPic);
                  }
                });


                //Another puzzle maker
                anotherPuzzle.setOnAction((ActionEvent e)->{
                    clickCounter=0;
                    showCount=2;

                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                    number.setText(Integer.toString(clickCounter));
                ForEasy();
                });


                //Load image of puzzle
                    for(i=0; i<3; i++){
                        for(j=0; j<3; j++)
                        {
                          puzzleImage[i][j] = new Image("project/pkg2015/res/easy/"+imageNumber+ ".png", 180, 170, false, false);
                          imageNumber++;
                        }
                    }

                //Randomised Grid Value
                    rand = randV.nextInt(10);
                    if(rand<=1)
                        rand=5;

                     j=0;
                     temp = rand+1;

                    while(rand>0 && j<26)
                    {
                       randVArr[j++]=rand;
                       rand--;
                    }

                   rand=temp;
                   while(rand<10 && j<26){
                       randVArr[j++]=rand;
                       rand++;
                   }

                  a=0;
                   for(i=0; i<3; i++){
                       for(j=0; j<3 && a<26; j++){
                           gridValue[i][j]= randVArr[a++];
                           if(gridValue[i][j]==9)
                           {
                               moveX=i;
                               moveY=j;
                           }
                       }

                   }


                //Create Puzzle ImageView
                   for(i=0; i<3; i++){
                       for(j=0; j<3; j++){
                           a = xValue(gridValue[i][j], 3);
                           b = yValue(gridValue[i][j], 3);

                           puzzleCell[i][j] = new ImageView(puzzleImage[a][b]);
                           final int fi=i, fj=j;
                           puzzleCell[i][j].setOnMouseClicked(e->{
                              if(checkIndex(fi, fj, 3)){

                                  Image tImage ;

                                  int x, y;

                                   tImage = puzzleCell[fi][fj].getImage();
                                   puzzleCell[moveX][moveY].setImage(tImage);

                                   puzzleCell[fi][fj].setImage(puzzleImage[2][2]);
                                   try{
                                       basicGridPane.add(puzzleCell[moveX][moveY], moveY, moveX);
                                       basicGridPane.add(puzzleCell[fi][fj], fj, fi);
                                   }catch(Exception exception){};

                                   gridValue[moveX][moveY]=gridValue[fi][fj];

                                   gridValue[fi][fj]=9;
                                   moveX = fi;
                                    moveY = fj;
                                   clickCounter++;
                                   number.setText(Integer.toString(clickCounter));

                                   if(checkEnd(3)){
                                         endTime = (int)System.currentTimeMillis()/1000;

                                            pclk.stop();
                                            psucces.stop();
                                            psucces.play();
                                          levelComplete.setText("Level Completed!!!!");
                                          levelComplete.getStyleClass().add("easy_level_Complete");
                                          elapsedTime = endTime - startTime;

                                           score = (200- elapsedTime)*2;
                                           if(score<=0)
                                               score = 10;
                                          Tscore.setText("Score : "+Integer.toString(score));
                                          Tscore.getStyleClass().add("easy_level_Complete");
                                          levelComplete.setMinSize(100, 300);
                                          afterComplete.getChildren().addAll(levelComplete, Tscore);
                                          basicBorderPane.setCenter(afterComplete);
                                   }
                                   else{
                                    pclk.stop();
                                  psucces.stop();
                                  pclk.play();
                                   }
                              }
                              else
                                  e.consume();
                           });
                           basicGridPane.add(puzzleCell[i][j], j, i);
                       }

                       }

                   basicGridPane.setVgap(1.5);
                   basicGridPane.setHgap(1.5);
                   basicBorderPane.setCenter(basicGridPane);

            }



    //Create Scene For Medium Level
            private void ForMedium() {

                //Saving Starting Time
                startTime = (int) System.currentTimeMillis()/1000;

                int i, j, a, b, imageNumber=1, rand, temp;
                basicGridPane = new GridPane();
                basicGridPane.getStyleClass().add("medium_Pane");

                //Change The current Level Name
                gameName.setText("Medium Puzzle");

                //Another puzzle maker
                anotherPuzzle.setOnAction(e->{
                    clickCounter = 0;

                    showCount=2;

                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                    number.setText(Integer.toString(clickCounter));
                ForMedium();
                });


                //Show Button

                showButton.setOnAction(e->{
                  if(showCount>=0)
                  {
                      showLife[showCount].setImage(cross);
                      showCount--;
                      showPic.setImage(new Image("project/pkg2015/res/medium/pic.jpg",600,500, false, false));
                    showPuzzlePic.ShowPic(showPic);
                  }
                });


                //Load image of puzzle
                    for(i=0; i<4; i++){
                        for(j=0; j<4; j++)
                        {
                          puzzleImage[i][j] = new Image("project/pkg2015/res/medium/"+imageNumber+ ".png", 140, 130, false, false);
                          imageNumber++;
                        }
                    }

                //Randomised Grid Value
                    rand = randV.nextInt(17);
                    if(rand<=1)
                        rand=5;

                     j=0;
                     temp = rand+1;

                    while(rand>0 && j<26)
                    {
                       randVArr[j++]=rand;
                       rand--;
                    }

                   rand=temp;
                   while(rand<17 && j<26){
                       randVArr[j++]=rand;
                       rand++;
                   }

                  a=0;
                   for(i=0; i<4; i++){
                       for(j=0; j<4 && a<26; j++){
                           gridValue[i][j]= randVArr[a++];
                           if(gridValue[i][j]==16)
                           {
                               moveX=i;
                               moveY=j;
                           }
                       }

                   }


                //Create Puzzle ImageView
                   for(i=0; i<4; i++){
                       for(j=0; j<4; j++){
                           a = xValue(gridValue[i][j], 4);
                           b = yValue(gridValue[i][j], 4);

                           puzzleCell[i][j] = new ImageView(puzzleImage[a][b]);
                           final int fi=i, fj=j;
                           puzzleCell[i][j].setOnMouseClicked(e->{
                              if(checkIndex(fi, fj, 4)){
                                  Image tImage ;

                                  int x, y;

                                   tImage = puzzleCell[fi][fj].getImage();
                                   puzzleCell[moveX][moveY].setImage(tImage);

                                   puzzleCell[fi][fj].setImage(puzzleImage[3][3]);
                                   try{ basicGridPane.add(puzzleCell[moveX][moveY], moveY, moveX);

                                   basicGridPane.add(puzzleCell[fi][fj], fj, fi);
                                   }catch(Exception exception){};

                                   gridValue[moveX][moveY]=gridValue[fi][fj];

                                   gridValue[fi][fj]=16;
                                   moveX = fi;
                                    moveY = fj;
                                   clickCounter++;
                                   number.setText(Integer.toString(clickCounter));

                                   if(checkEnd(4)){
                                        endTime = (int)System.currentTimeMillis()/1000;
                                        pclk.stop();
                                  psucces.stop();
                                  psucces.play();
                                      levelComplete.setText("Level Completed!!!!");
                                      levelComplete.getStyleClass().add("medium_level_Complete");
                                             elapsedTime = endTime - startTime;

                                           score = (1000- elapsedTime)*2;
                                           if(score<=0)
                                               score = 10;
                                          Tscore.setText("Score : "+Integer.toString(score));
                                          Tscore.getStyleClass().add("easy_level_Complete");
                                          levelComplete.setMinSize(100, 300);
                                          afterComplete.getChildren().addAll(levelComplete, Tscore);
                                          basicBorderPane.setCenter(afterComplete);
                                   }
                                   else{
                                        pclk.stop();
                                  psucces.stop();
                                  pclk.play();
                                   }

                              }
                              else
                                  e.consume();
                           });
                           basicGridPane.add(puzzleCell[i][j], j, i);
                       }

                       }

                   basicGridPane.setVgap(1.5);
                   basicGridPane.setHgap(1.5);
                   basicBorderPane.setCenter(basicGridPane);

            }



    //Create Scene For Hard Level
            private void ForHard() {

                //Saving Starting Time
                startTime = (int)System.currentTimeMillis()/1000;
                gameName.setText("Hard Puzzle");


                int i, j, a, b, imageNumber=1, rand, temp;
                basicGridPane = new GridPane();


                //Show Button

                showButton.setOnAction(e->{
                  if(showCount>=0)
                  {
                      showLife[showCount].setImage(cross);
                      showCount--;
                      showPic.setImage(new Image("project/pkg2015/res/hard/pic.jpg",600,500, false, false));
                      showPuzzlePic.ShowPic(showPic);
                  }
                });

                //Another puzzle maker
                anotherPuzzle.setOnAction(e->{
                 clickCounter=0;

                 showCount=2;

                     try{
                         showLife[0].setImage(life);
                           showLife[1].setImage(life);
                             showLife[2].setImage(life);
                     }catch(Exception exception){};

                 number.setText(Integer.toString(clickCounter));
                ForHard();
                });




                //Load image of puzzle
                    for(i=0; i<5; i++){
                        for(j=0; j<5; j++)
                        {
                          puzzleImage[i][j] = new Image("project/pkg2015/res/hard/"+imageNumber+ ".png", 110, 105, false, false);
                          imageNumber++;
                        }
                    }

                //Randomised Grid Value
                    rand = randV.nextInt(26);
                    if(rand<=1)
                        rand=5;

                     j=0;
                     temp = rand+1;

                    while(rand>0 && j<26)
                    {
                       randVArr[j++]=rand;
                       rand--;
                    }

                   rand=temp;
                   while(rand<26 && j<26){
                       randVArr[j++]=rand;
                       rand++;
                   }

                  a=0;
                   for(i=0; i<5; i++){
                       for(j=0; j<5 && a<26; j++){
                           gridValue[i][j]= randVArr[a++];
                           if(gridValue[i][j]==25)
                           {
                               moveX=i;
                               moveY=j;
                           }
                       }

                   }


                //Create Puzzle ImageView
                   for(i=0; i<5; i++){
                       for(j=0; j<5; j++){
                           a = xValue(gridValue[i][j], 5);
                           b = yValue(gridValue[i][j], 5);

                           puzzleCell[i][j] = new ImageView(puzzleImage[a][b]);
                           final int fi=i, fj=j;
                           puzzleCell[i][j].setOnMouseClicked(e->{
                              if(checkIndex(fi, fj, 5)){
                                  Image tImage ;

                                  int x, y;

                                   tImage = puzzleCell[fi][fj].getImage();
                                   puzzleCell[moveX][moveY].setImage(tImage);

                                   puzzleCell[fi][fj].setImage(puzzleImage[4][4]);
                                   try{ basicGridPane.add(puzzleCell[moveX][moveY], moveY, moveX);

                                   basicGridPane.add(puzzleCell[fi][fj], fj, fi);
                                   }catch(Exception exception){};

                                   gridValue[moveX][moveY]=gridValue[fi][fj];

                                   gridValue[fi][fj]=25;
                                   moveX = fi;
                                    moveY = fj;
                                   clickCounter++;
                                   number.setText(Integer.toString(clickCounter));

                                   if(checkEnd(5)){
                                       endTime = (int) System.currentTimeMillis()/1000;
                                       elapsedTime = endTime-startTime;

                                        pclk.stop();
                                        psucces.stop();
                                        psucces.play();

                                      levelComplete.setText("Level Completed!!!!");
                                      levelComplete.getStyleClass().add("hard_level_Complete");
                                          score = (2000- elapsedTime)*2;
                                           if(score<=0)
                                               score = 10;
                                          Tscore.setText("Score : "+Integer.toString(score));
                                          Tscore.getStyleClass().add("easy_level_Complete");
                                          levelComplete.setMinSize(100, 300);
                                          afterComplete.getChildren().addAll(levelComplete, Tscore);
                                          basicBorderPane.setCenter(afterComplete);

                                   }
                                   else{
                                        pclk.stop();
                                  psucces.stop();
                                  pclk.play();
                                   }

                              }
                              else
                                  e.consume();
                           });
                           basicGridPane.add(puzzleCell[i][j], j, i);
                       }

                       }

                   basicGridPane.setVgap(1.5);
                   basicGridPane.setHgap(1.5);
                   basicGridPane.getStyleClass().add("hard_Pane");
                   basicBorderPane.setCenter(basicGridPane);
            }



    //For the X coordinate value of the image
        private int xValue(int i, int imInRow) {
             if(i<=imInRow)
                 return 0;

             else if(i>imInRow && i<=(imInRow*2))
                 return 1;
             else if(i>(imInRow*2) && i<=(imInRow*3))
                 return 2;
             else if(i>(imInRow*3) && i<=(imInRow*4))
                 return 3;


             return 4;
             }
    //For the Y coordinate value of the image
    private int yValue(int j, int imInRow) {
          if((j%imInRow) ==0)
              return (imInRow-1);
              else
              return ((j%imInRow)-1);
          }



    //Check the index number of the move image
    private boolean checkIndex( int i,  int j, int imNumber) {


        if( (i+1)<imNumber && gridValue[i+1][j]==(imNumber*imNumber))
         {

             return true;
         }
         else if((i-1)>=0 && gridValue[i-1][j]==(imNumber*imNumber))
         {

             return true;
         }
         else if((j+1)<imNumber && gridValue[i][j+1]==(imNumber*imNumber) )
         {

             return true;
         }

          else if((j-1)>=0 && gridValue[i][j-1]==(imNumber*imNumber))
         {

             return true;
         }

         return false;
        }


    //Check if level is complete or not
    private boolean checkEnd(int imInRow) {
        int a, b, n=1;
        for(a=0; a<imInRow; a++){
            for(b=0; b<imInRow; b++){
                if(gridValue[a][b]!=n)
                {
                    return false;
                }
                n++;
            }
        }
      return true;
    }

}
