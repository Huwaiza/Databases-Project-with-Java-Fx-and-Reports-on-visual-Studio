package ProjectDatabases;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmbeddingLayouts
{
    Stage window;
    Scene s1,s2;
    public void chooseLayout()
    {
        window = new Stage();
        window.setTitle("Different layouts");
        window.setMinWidth(300);

        Label l = new Label("Choose any layout");
        Button h = new Button("HBox demonstration");
        h.setOnAction(
                e->{
                    Hlayout();
                }
        );
        Button v = new Button("VBox demonstration");
        v.setOnAction(
                e->{
                    Vlayout();
                }
        );
        Button embd = new Button("Embeded layout demonstration");
        embd.setOnAction(
                e->{
                    embeded();
                }
        );

        VBox lay  = new VBox(10);
        lay.getChildren().addAll(l,h,v,embd);
        lay.setAlignment(Pos.CENTER);

        Scene s = new Scene(lay,300,300);
        window.setScene(s);
        window.show();
    }
    public void Hlayout()
    {
        window.setTitle("Menu bar");
        HBox  topmenu = new HBox(5);
        Button b1 = new Button("File");
        Button b2 = new Button("Edit");
        Button b3 = new Button("view");
        Button b4 = new Button("Navigate");
        topmenu.getChildren().addAll(b1,b2,b3,b4);
        Scene s1 = new Scene(topmenu,300,300);
        window.setScene(s1);
        window.show();

    }
    public void Vlayout()
    {
        window.setTitle("Menu bar");
        VBox  leftmenu = new VBox(10);
        Button b5 = new Button("Exa");
        Button b6 = new Button("peta");
        Button b7 = new Button("tera");
        Button b8 = new Button("gia");
        leftmenu.getChildren().addAll(b5,b6,b7,b8);
        Scene s1 = new Scene(leftmenu,300,300);
        window.setScene(s1);
        window.show();

    }
    public void embeded()
    {
        window.setTitle("embeded layout bar");

        VBox  leftmenu = new VBox(10);
        Button b43 = new Button("Exa");
        Button b63 = new Button("peta");
        Button b73 = new Button("tera");
        Button b83 = new Button("gia");
        leftmenu.getChildren().addAll(b43,b63,b73,b83);

        HBox  topmenu = new HBox(5);
        Button b1 = new Button("File");
        Button b2 = new Button("Edit");
        Button b3 = new Button("view");
        Button b4 = new Button("Navigate");
        topmenu.getChildren().addAll(b1,b2,b3,b4);

        BorderPane bp = new BorderPane();
        bp.setTop(topmenu);
        bp.setLeft(leftmenu);
        Scene sceness = new Scene(bp,300,300);
        window.setScene(sceness);
        window.show();

    }


}
