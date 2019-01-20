package ProjectDatabases;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainClass extends Application {
    public mainClass() {
    }
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws IOException {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("mainforfxml.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("internet Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

