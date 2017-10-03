/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2015;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Shohag
 */
public class ShowPuzzlePic {
    
    public void ShowPic(ImageView imageView){
        Stage  showHelp = new Stage();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(imageView);
        vBox.getStyleClass().add("my_Pane");
        
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("project/pkg2015/myStyle.css");
        
        showHelp.setMinHeight(500);
        showHelp.setMinWidth(600);
        showHelp.setResizable(false);
        showHelp.setTitle("Image Show Help");
        showHelp.getIcons().add(new Image("project/pkg2015/res/Icon/icon.png"));
        showHelp.initModality(Modality.APPLICATION_MODAL);
        showHelp.setScene(scene);
        showHelp.show();
    }
    
}
