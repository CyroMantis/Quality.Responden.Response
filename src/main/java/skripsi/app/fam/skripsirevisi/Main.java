package skripsi.app.fam.skripsirevisi;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author qifli
 */
public class Main extends Application {


    public double xOffset = 0;
    public double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("LoginInterface.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Decision Support FAM");

        /*
        Testing FXML FILE Resolution 
        590, 460 (LoginInterface)
        1200, 700 (CoreInterface)
        590, 670 (RegisterInterface)
         */

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(root, 590, 460));
        //primaryStage.getIcons().add(new javafx.scene.image.Image("C:\\Users\\qifli\\Documents\\NetBeansProjects\\AplikasiSkripsi\\src\\Resource\\Fam.png"));

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
