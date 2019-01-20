package ProjectDatabases;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainforfxml_Controller implements Initializable {

    private BorderPane bd = new BorderPane();
    public Button closeButton;

    public void initialize(URL Location, ResourceBundle resources) {
        assert this.bd != null : "fx:id=\"internet\" was not injected: check your FXML file 'internet.fxml'.";
    }
//    @FXML
//    private void Close() {
//        Window stage = this.closeButton.getScene().getWindow();
//        stage.hide();
//    }
    @FXML
    private void internet() throws IOException {
//        Close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("internet.fxml"));
        Scene scene = new Scene(root);
//        URL url = this.getClass().getResource("Employee.css");
//        String css = url.toExternalForm();
//        scene.getStylesheets().add(css);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Internet");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
