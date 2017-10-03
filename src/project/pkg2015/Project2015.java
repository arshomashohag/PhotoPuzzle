 
package project.pkg2015;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Shohag
 */

public class Project2015 extends Application {
    
    protected SceneCreater sceneCreater = new SceneCreater();
    
    Level_Easy easyLevel = new Level_Easy();
    
    
    @Override
    public void start(Stage primaryStage) {
       
        primaryStage.setTitle("S-Puzzle");
         
        primaryStage.getIcons().add(new Image("project/pkg2015/res/Icon/icon.png"));
        easyLevel.StartEasyLevel(primaryStage,sceneCreater); 
      
    }
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
   
}
