package skripsi.app.fam.skripsirevisi;

import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author qifli
 */
public class LoginInterfaceController implements Initializable {

    public double xOffset = 0;
    public double yOffset = 0;

    @FXML
    private TextField txtpass;
    @FXML
    private TextField txtus;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btncancle;
    @FXML
    private Label erorlgn;
    @FXML
    private Label daftarHyper;
    private CheckBox checkRememberme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //      TODO
    }
    @FXML
    private void btnloginAction(ActionEvent event) {
        if (txtus.getText().isBlank() == false && txtus.getText().isBlank() == false) {
            checkLogin();
        } else {
            erorlgn.setText("Harap isi Username dan Password anda !");
        }
    }

    @FXML
    private void btncancleAction(ActionEvent event) {
        Stage stage = (Stage) btncancle.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    private void hyperdaftar(MouseEvent event) {
        daftarAkun();
        Stage stage = (Stage) daftarHyper.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void KeyPressedActionListener(KeyEvent event) throws IOException, SQLException {
        if (txtus.getText().isBlank() == false && txtus.getText().isBlank() == false && event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Listener Enter Berhasil");
            checkLogin();
        }
    }

    private void checkLogin() {
        DatabaseConnection connections = new DatabaseConnection();
        Connection connectDB = connections.getConnection();
        String ChekcLogin = "SELECT count(1) FROM tb_useraccount where username = '" + txtus.getText() + "' AND password ='" + txtpass.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(ChekcLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    erorlgn.setText("Berhasil Login");
                    goingInside();
                    Stage stage = (Stage) btnlogin.getScene().getWindow();
                    stage.close();
                } else if (queryResult.getInt(1) == 0) {
                    erorlgn.setText("Invalid, Coba Lagi");
                } else {
                    erorlgn.setText("User Account Tidak tersedia");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void daftarAkun() {

        try {
            //URL url = new File("src/QualityResponAppFAM/RegisterInterface.fxml").toURI().toURL();
            //Parent root = FXMLLoader.load(url);
            Parent root = FXMLLoader.load(getClass().getResource("RegisterInterface.fxml"));
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 590, 670));
            loginStage.getIcons().add(new javafx.scene.image.Image("C:\\Users\\qifli\\Documents\\NetBeansProjects\\AplikasiSkripsi\\src\\Resource\\Fam.png"));

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
                    loginStage.setX(event.getScreenX() - xOffset);
                    loginStage.setY(event.getScreenY() - yOffset);
                }
            });

            loginStage.setResizable(false);
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setTitle("Decision Support FAM");

            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void goingInside() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Core.fxml"));
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 978, 700));
            loginStage.getIcons().add(new javafx.scene.image.Image("C:\\Users\\qifli\\Documents\\NetBeansProjects\\AplikasiSkripsi\\src\\Resource\\Fam.png"));
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
                    loginStage.setX(event.getScreenX() - xOffset);
                    loginStage.setY(event.getScreenY() - yOffset);
                }
            });
            loginStage.setResizable(false);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setTitle("Decision Support FAM");

            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
