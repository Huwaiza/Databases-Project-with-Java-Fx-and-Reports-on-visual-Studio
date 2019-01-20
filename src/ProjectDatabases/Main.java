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
import javafx.stage.Stage;

public class Main extends Application{ // For Fx application we are gonna extend our program with Application

    Stage window;
    Scene scene1,scene2;
    boolean result=true;

    public static void main(String[] args)
    {
        launch(args); // Firstly its going to launch
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Civil Engineers");

//        Button button1 = new Button();
//        button1.setText("Click me once");
//        layout.getChildren().add(button1);
        //        button1.setOnAction(new EventHandler<ActionEvent>(){          //Anonymous inner class
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.close();
//                System.out.println("Civil Fucking engineering");
//            }
//       StackPane layout  = new StackPane();

        window = primaryStage;
        Label label1 = new Label("Welcome to first screen");
        Button button = new Button();
        button.setText("Go to Scene 2");

        Button confirm = new Button();
        confirm.setText("Enter Confirmation scene");

        Button button4 = new Button();
        button4.setText("Close scene");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Button propclose = new Button();
        propclose.setText("Close this properly");

        Button lays = new Button();
        lays.setText("Enter Layouts menu");

        lays.setOnAction(
                e->{
                    EmbeddingLayouts embeddingLayouts = new EmbeddingLayouts();
                    embeddingLayouts.chooseLayout();
                }
        );

        propclose.setOnAction(
                e -> {
                    closeProgram();
                }
        );

        button.setOnAction(e-> {
            window.setScene(scene2);
        });    //lambda expressions

        button4.setOnAction(e-> {
            window.close();
        });    //lambda expressions

        confirm.setOnAction(
                e->{
                    result = ConfirmBox.display("What you want to do!","Are you sure you want to do this");
                   System.out.println(result);
                }
        );
        //layout1

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,button,button4,confirm,propclose,lays);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1,300,300);


        Button button1 = new Button();
        button1.setText("Go to scene 3");

        Button button2 = new Button();
        button2.setText("Go to scene 1");

        Button button3 = new Button();
        button3.setText("Close scene");

        button1.setOnAction(
                e->{
//                    window.setScene(scene1);
                    Alert.Display("Close it","It will not close until you close it");
                }
        );
        button2.setOnAction(
                e->{
                    window.setScene(scene1);
                }
        );
        button3.setOnAction(
                e->{
                    window.close();
                }
        );
        //layout2
        //StackPane layout2 = new StackPane();
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(button1,button2,button3);
        scene2 = new Scene(layout2,300,200);
        layout2.setAlignment(Pos.CENTER);

        window.setScene(scene1);
        window.show();

    }
    public void closeProgram()
    {
        Boolean sure = ConfirmBox.display("SURE ?","You sure you want to close?");
        System.out.println(sure);
        if(sure)
        {
            window.close();
            System.out.println("File is saved brow");
        }

    }

}
