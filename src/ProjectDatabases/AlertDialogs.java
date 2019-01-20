package ProjectDatabases;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertDialogs{
    public static void display(String title, String message)
    {
        Stage window =  new Stage();
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMaxHeight(200);

        Label msg = new Label();
        msg.setText(message);
        Button okButton = new Button();
        okButton.setText("Ok");
        okButton.setOnAction(
                e->{
                    window.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(msg,okButton);
        layout.setAlignment(Pos.CENTER);
        Scene sc = new Scene(layout);

        window.setScene(sc);
        window.show();

    }
}
