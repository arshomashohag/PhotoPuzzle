/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2015;

import javafx.stage.Stage;

/**
 *
 * @author Shohag
 */
public class Level_Easy {
     private Stage easyWindow;
    private SceneCreater sceneCreater ;
      
   void StartEasyLevel(Stage stage, SceneCreater sceneCreater) { 
        easyWindow = stage;
        easyWindow.setMinHeight(700);
        easyWindow.setMinWidth(900);
        easyWindow.setResizable(false); 
        easyWindow.setScene(sceneCreater.CreateScene(9));
        easyWindow.show();
    }
    
}
