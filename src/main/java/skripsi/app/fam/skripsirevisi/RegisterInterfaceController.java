package skripsi.app.fam.skripsirevisi;

import skripsi.app.fam.skripsirevisi.Model.UserDaoModel.User;
import skripsi.app.fam.skripsirevisi.Model.UserDaoModel.UserDao;
import skripsi.app.fam.skripsirevisi.Model.UserDaoModel.UserDaoImpl;
import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
public class RegisterInterfaceController implements Initializable {

    public double xOffset = 0;
    public double yOffset = 0;
    @FXML
    private TextField namaDepanBox;
    @FXML
    private TextField namaBelakangBox;
    @FXML
    private TextField namaLengkapBox;
    @FXML
    private TextField NIDN_BOX;
    @FXML
    private TextField EmailBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private CheckBox cekBoxSetuju;
    @FXML
    private Button btnDaftar;
    @FXML
    private Button btnMasuk;
    @FXML
    private PasswordField PasswordBoxRegis;
    @FXML
    private PasswordField CekBoxPasswordRegis;
    @FXML
    private Label ConfirmationEventRegister;
    @FXML
    private Label ConfirmationEventPasswordCekVer;
    @FXML
    private Label ConfirmationEventRegisterEror;

    @FXML
    private TextField nomorTelphoneBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnDaftarAction(ActionEvent event) throws SQLException {
        if (PasswordBoxRegis.getText().equals(CekBoxPasswordRegis.getText())) {
            if (PasswordBoxRegis.getText().isBlank() == false && CekBoxPasswordRegis.getText().isBlank() == false) {

                ConfirmationEventPasswordCekVer.setText("✓");
                ConfirmationEventPasswordCekVer.setText("");
                registUser();;

            } else {
                ConfirmationEventRegisterEror.setText("Lengkapi Biodata Anda Terlebih Dahulu!");
            }
        } else {
            ConfirmationEventPasswordCekVer.setText("Password Tidak Sesuai!");
        }
    }

    @FXML
    private void btnMasukAction(ActionEvent event) {

        LoginStage();
        Stage stage = (Stage) btnMasuk.getScene().getWindow();
        stage.close();
        //Platform.exit(); Ultimate Exit Wahahahaha
    }

    public void LoginStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginInterface.fxml"));

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 590, 460));
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

    public void registUser() throws SQLException {
        if (namaDepanBox.getText().equals("") && namaDepanBox.getText().equals("") && namaDepanBox.getText().equals("")
                && NIDN_BOX.getText().equals("") && EmailBox.getText().equals("") && usernameBox.getText().equals("")
                && PasswordBoxRegis.getText().equals("")) {
            ConfirmationEventRegister.setText("Lengkapi Biodata Anda Terlebih Dahulu");
        } else {

            DatabaseConnection connections = new DatabaseConnection();
            Connection connectDB = connections.getConnection();
            String ChekcLogin = "SELECT count(1) FROM tb_detail_account where nidn = '" + NIDN_BOX.getText() + "'";

            User user = new User();
            User user2 = new User();

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(ChekcLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    System.out.println("daftar gagal");
                    ConfirmationEventRegisterEror.setText("Error! NIDN Sudah Terdaftar ");
                    ConfirmationEventRegister.setText("");
                } else if (queryResult.getInt(1) == 0) {
                    if (namaDepanBox.getText().equals("") && namaDepanBox.getText().equals("")
                            && namaDepanBox.getText().equals("") && NIDN_BOX.getText().equals("")
                            && EmailBox.getText().equals("")) {
                        ConfirmationEventRegisterEror.setText("Lengkapi Biodata Anda Terlebih Dahulu");
                        ConfirmationEventRegister.setText("");
                    } else {
                        ConfirmationEventRegister.setText("Berhasil Daftar");

                        UserDao userDao = new UserDaoImpl();
                        user.setNidn(NIDN_BOX.getText());
                        user.setNama_depan(namaDepanBox.getText());
                        user.setNama_belakang(namaBelakangBox.getText());
                        user.setNama_lengkap(namaLengkapBox.getText());
                        user.setEmail(EmailBox.getText());
                        user.setNo_telp(nomorTelphoneBox.getText());

                        user2.setDtl_username(usernameBox.getText());
                        user2.setDtl_password(PasswordBoxRegis.getText());
                        user2.setNidn(NIDN_BOX.getText());
                        int result = userDao.insertDetail(user);
                        int result2 = userDao.insertAccount(user2);
                        System.out.println("daftar berhasil");
                        LoginStage();
                        Stage stage = (Stage) btnDaftar.getScene().getWindow();
                        stage.close();
                    }

                } else {

                }
            }

        }
    }
    @FXML
    private void cekBoxSetujuAction(ActionEvent event) {
        if (cekBoxSetuju.isSelected()) {
            btnDaftar.setDisable(false);
        } else {
            btnDaftar.setDisable(true);
        }
    }
}
