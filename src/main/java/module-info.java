module skripsi.app.fam.skripsirevisi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens skripsi.app.fam.skripsirevisi to javafx.fxml;
    opens skripsi.app.fam.skripsirevisi.Model.InputsDaoModel;
    opens skripsi.app.fam.skripsirevisi.Model.HasilProsesModel;
    opens skripsi.app.fam.skripsirevisi.Model.UserDaoModel;
    exports skripsi.app.fam.skripsirevisi;
}