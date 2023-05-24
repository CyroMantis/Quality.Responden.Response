package skripsi.app.fam.skripsirevisi.Utill;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author qifli
 */
// kompilasi AlertBox
public class Alertbox {

    //Eror Box (X)
    public static void ErorBox(String PesanInfo, String HeaderText, String Title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(PesanInfo);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    //Info Log (!)
    public static void InfoBox(String PesanInfo, String HeaderText, String Title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(PesanInfo);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.show();
       
    }

    //Confirmation Box (?)
    public static void ConfirmationBox(String PesanInfo, String HeaderText, String Title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(PesanInfo);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    // Warning Box (<!>)
    public static void WarningBox(String PesanInfo, String HeaderText, String Title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(PesanInfo);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

}
