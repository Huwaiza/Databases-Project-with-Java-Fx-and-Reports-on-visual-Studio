package ProjectDatabases;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Alert {
    public static void Display(String title, String message)
    {
        Stage window  = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label1 = new Label();
        label1.setText(message);

        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setOnAction(
                event -> {
                    window.close();
                }
        );


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,closeButton);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout,300,300);
        window.setScene(scene);
        window.showAndWait();

    }

}
