package skripsi.app.fam.skripsirevisi;

import skripsi.app.fam.skripsirevisi.Model.HasilProsesModel.*;
import skripsi.app.fam.skripsirevisi.Model.InputsDaoModel.*;
import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import static skripsi.app.fam.skripsirevisi.Utill.Alertbox.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * FXML Controller class
 *
 * @author qifli
 */
public class CoreController implements Initializable {

    public double xOffset = 0;
    public double yOffset = 0;

    //TextField FXML
    @FXML
    private TextField inputPPJSectionCore;
    @FXML
    private TextField inputPADSectionCore;
    @FXML
    private TextField inputPRSSectionCore;
    @FXML
    private TextField inputPTKSectionCore;
    @FXML
    private TextField inputPKMHSectionCore;
    @FXML
    private TextField inputNimSectionCore;
    @FXML
    private TextField inputNamaSectionCore;
    @FXML
    private TextField tidakMemuaskanBox;
    @FXML
    private TextField cukupMemuaskanBox;
    @FXML
    private TextField sangatMemuaskanBox;
    @FXML
    private TextField pencarianBox;

    //Groun FXML
    @FXML
    private Group logoutButton;

    //ComboBox FXML
    @FXML
    private ComboBox<String> inputSemesterSectionCore;
    @FXML
    private ComboBox<String> inputRegulerSectionCore;

    //Button FXML
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonCkear;
    @FXML
    private Button prosesButton;

    //Table FXML
    @FXML
    private TableView<Inputs> tableInputView;

    @FXML
    private TableColumn<Inputs, String> nimColumnInput;
    @FXML
    private TableColumn<Inputs, String> namaColumnInput;
    @FXML
    private TableColumn<Inputs, String> semesterColumnInput;
    @FXML
    private TableColumn<Inputs, String> regulerColumnInput;
    @FXML
    private TableColumn<Inputs, Integer> ppjColumnInput;
    @FXML
    private TableColumn<Inputs, Integer> padColumnInput;
    @FXML
    private TableColumn<Inputs, Integer> prsColumnInput;
    @FXML
    private TableColumn<Inputs, Integer> ptkColumnInput;
    @FXML
    private TableColumn<Inputs, Integer> pkmhColumnInput;

    @FXML
    private TableView<HasilProses> tableProsesView;
    @FXML
    private TableColumn<HasilProses, String> nimColumnProses;
    @FXML
    private TableColumn<HasilProses, String> namaColumnProses;
    @FXML
    private TableColumn<HasilProses, Double> hasilColumnProses;
    @FXML
    private TableColumn<HasilProses, String> keputusanColumnProses;

    //Array List Untuk Listing Item Combobox Dan Table
    ObservableList<String> SemesterListItem = FXCollections.observableArrayList("Semester", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    ObservableList<String> RegulerListItem = FXCollections.observableArrayList("Reguler", "A", "B", "CK");

    //list untuk tableview
    List<Inputs> containList;
    List<HasilProses> containerList;

    //inisialisasi class
    InputsDao inputsDaoImpl = new InputsDaoImpl();
    HasilProsesDao hasilProsesDaoImpl = new HasilProsesDaoImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Start Up Setting
        ComboBoxListingItem();
    }

    @FXML // Aksi Untuk Tombol logout yang mirip gambar panah <-  
    private void logoutButtonAction(MouseEvent event) {
        logOutStageAction();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    // Method yang dipakai buat logout
    public void logOutStageAction() {

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

    @FXML // Aksi untuk tombol Save/Simpan guna untuk memasukan data kedalam database 
    private void buttonSaveAction(ActionEvent event) {
        if (inputNamaSectionCore.getText().equals("") || inputNimSectionCore.getText().equals("") || inputPPJSectionCore.getText().isBlank() || inputPADSectionCore.getText().isBlank() || inputPRSSectionCore.getText().isBlank() || inputPTKSectionCore.getText().isBlank() ||  inputPKMHSectionCore.getText().isBlank()) {
            System.out.println(" Pesan Kurang");
            WarningBox("Harap Lengakapi Data Terlebih Dahulu", null, "Save Confirmation");
        } else if (inputSemesterSectionCore.getValue() == "Semester" || inputRegulerSectionCore.getValue() == "Reguler" ) {
            System.out.println(" Semester dan Reguler Belum Terisi!");
            WarningBox("Harap Lengakapi Data Terlebih Dahulu!", null, "Save Confirmation");
        } else {
            try {
                Inputs inputs = new Inputs();
                int BP = Integer.parseInt(inputPPJSectionCore.getText());
                int BA = Integer.parseInt(inputPADSectionCore.getText());
                int BS = Integer.parseInt(inputPRSSectionCore.getText());
                int BPK = Integer.parseInt(inputPTKSectionCore.getText());
                int BK = Integer.parseInt(inputPKMHSectionCore.getText());

                inputs.setNim(inputNimSectionCore.getText());
                inputs.setNama(inputNamaSectionCore.getText());
                inputs.setSemester((String) inputSemesterSectionCore.getValue());
                inputs.setReguler((String) inputRegulerSectionCore.getValue());
                inputs.setP_pembelajaran(BP);
                inputs.setP_administrasi(BA);
                inputs.setP_sarana(BS);
                inputs.setP_perpustakaan(BPK);
                inputs.setP_kemahasiswaan(BK);

                InputsDao inputsDao = new InputsDaoImpl();

                int result = inputsDao.inputs(inputs);

                System.out.println(result + "Simpan Berhasil");
                InfoBox("Simpan Berhasil", null, "Save Confirmation");
                UpdateTableInputView();
                clearField();
            } catch (Exception e) {
                InfoBox("Data Sudah Ada Harap Periksa Kembali", null, "Save Confirmation");
                e.getCause();
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void buttonEditAction(ActionEvent event) {
        if (inputNimSectionCore.getText().equals("") || inputNimSectionCore.getText().isEmpty()) {
            System.out.println("Isi Nim Dan Nama dulu");
            WarningBox("Harap pilih atau isi Nim terlebih dahulu", null, "Update Confirmation");
        } else {
            try {

                Inputs inputsu = new Inputs();
                int BP = Integer.parseInt(inputPPJSectionCore.getText());
                int BA = Integer.parseInt(inputPADSectionCore.getText());
                int BS = Integer.parseInt(inputPRSSectionCore.getText());
                int BPK = Integer.parseInt(inputPTKSectionCore.getText());
                int BK = Integer.parseInt(inputPKMHSectionCore.getText());

                inputsu.setNama(inputNamaSectionCore.getText());
                inputsu.setSemester((String) inputSemesterSectionCore.getValue());
                inputsu.setReguler((String) inputRegulerSectionCore.getValue());
                inputsu.setP_pembelajaran(BP);
                inputsu.setP_administrasi(BA);
                inputsu.setP_sarana(BS);
                inputsu.setP_perpustakaan(BPK);
                inputsu.setP_kemahasiswaan(BK);
                inputsu.setNim(inputNimSectionCore.getText());
                InputsDao inputsDao = new InputsDaoImpl();

                inputsDao.update(inputsu);

                System.out.println("Update berhasil");
                InfoBox("Edit Berhasil", null, "Save Confirmation");

                UpdateTableInputView();
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
        }

    }

    @FXML // Fungsi tombol detele
    private void buttonDeleteAction(ActionEvent event) {
        if (inputNimSectionCore.getText().equals("") || inputNimSectionCore.getText().isEmpty()) {
            System.out.println("Isi Kotak Nim dan Nama Dulu");
            WarningBox("Harap isi Nim", null, "Save Confirmation");
        } else {
            try {
                InputsDao inputsDao = new InputsDaoImpl();
                Inputs inputs = inputsDao.get(inputNimSectionCore.getText(), inputNamaSectionCore.getText());
                System.out.println("Delete Berhasil");

                int result = inputsDao.delete(inputs);

                System.out.println(result);
                ConfirmationBox("Hapus Berhasil", null, "Hapus Confirmation");
                UpdateTableInputView();
                UpdateTableHasilView();
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void buttonClearAction(ActionEvent event) throws SQLException {

        Alert abx= new Alert(Alert.AlertType.CONFIRMATION);
        abx.initStyle(StageStyle.TRANSPARENT);
        abx.setTitle("Konfirmasi");
        abx.setHeaderText("Apa Anda Yakin? ");
        abx.setContentText("Pastikan Anda Sudah yakin jika iya (Ok) jika tidak (Cancle) ");

        Optional<ButtonType> Ars = abx.showAndWait();
        if (Ars.get() == ButtonType.OK){
            hasilProsesDaoImpl.clear();
            inputsDaoImpl.clear();
            UpdateTableHasilView();
            UpdateTableInputView();
            clearField();
            System.out.println("Clear: Ok");
        } else if (Ars.get() == ButtonType.CANCEL) {
            abx.close();
            System.out.println("Clear: Cancle");
        }
    }

    @FXML
    // Aksi yang akan dilakukan oleh tombol Proses pada Aplikasi
    private void prosesButtonAction(ActionEvent event) throws SQLException {
        ConfirmationBox("Proses Akan Berjalan Harap Tunggu Sebentar", null, "Proses");
        hasilProsesDaoImpl.clear();
        ProsesFuzzy fuzzy = new ProsesFuzzy();
        for (Inputs n : tableInputView.getItems()) {
            fuzzy.respondenNim = (nimColumnInput.getCellData(n));
            fuzzy.respondenNama = (namaColumnInput.getCellData(n));
            fuzzy.kondisi_ppj = (ppjColumnInput.getCellData(n));
            fuzzy.kondisi_pad = (padColumnInput.getCellData(n));
            fuzzy.kondisi_prs = (prsColumnInput.getCellData(n));
            fuzzy.kondisi_ptk = (ptkColumnInput.getCellData(n));
            fuzzy.kondisi_pkmh = (pkmhColumnInput.getCellData(n));
            fuzzy.kondisi_total = fuzzy.kondisi_ppj + fuzzy.kondisi_pad + fuzzy.kondisi_prs + fuzzy.kondisi_ptk + fuzzy.kondisi_pkmh;

            //Testing Console Output Proses
            System.out.println("\n NIM: " + nimColumnInput.getCellData(n) + " Nama :" + namaColumnInput.getCellData(n) + " ");
            System.out.println(" Pembelajaran : " + fuzzy.kondisi_ppj);
            System.out.println(" Administrasi : " + fuzzy.kondisi_pad);
            System.out.println(" Prasarana : " + fuzzy.kondisi_prs);
            System.out.println(" Perpustakaan : " + fuzzy.kondisi_ptk);
            System.out.println(" Kemahasiswaan : " + fuzzy.kondisi_pkmh);
            System.out.println(" Total Penilaian : " + fuzzy.kondisi_total);

            fuzzy.comput_membership();
            fuzzy.Apply_RuleFAM();
            fuzzy.SaveProses();
            UpdateTableHasilView();
            HasiKeputusanKategori();
        }

    }


    @FXML // Aksi untuk mendapatkan data ketika Item/data tertentu dipilih dengan menglic data pada table
    private void selectDataTableInputView(MouseEvent event) {
        String ppb = String.valueOf(tableInputView.getSelectionModel().getSelectedItem().getP_pembelajaran());
        String pas = String.valueOf(tableInputView.getSelectionModel().getSelectedItem().getP_administrasi());
        String psa = String.valueOf(tableInputView.getSelectionModel().getSelectedItem().getP_sarana());
        String ppk = String.valueOf(tableInputView.getSelectionModel().getSelectedItem().getP_perpustakaan());
        String pkm = String.valueOf(tableInputView.getSelectionModel().getSelectedItem().getP_kemahasiswaan());

        if (event.getClickCount() == 2) {
            inputNimSectionCore.setText(tableInputView.getSelectionModel().getSelectedItem().getNim());
            inputNamaSectionCore.setText(tableInputView.getSelectionModel().getSelectedItem().getNama());
            inputSemesterSectionCore.setValue(tableInputView.getSelectionModel().getSelectedItem().getSemester());
            inputRegulerSectionCore.setValue(tableInputView.getSelectionModel().getSelectedItem().getReguler());
            inputPPJSectionCore.setText(ppb);
            inputPADSectionCore.setText(pas);
            inputPRSSectionCore.setText(psa);
            inputPTKSectionCore.setText(ppk);
            inputPKMHSectionCore.setText(pkm);
        }
        else{
            System.out.println("Data Tidak Ada! Ulangi");
        }
    }

    // Inisialisasi prosedur combobox item beserta update table
    private void ComboBoxListingItem() {
        inputSemesterSectionCore.setValue("Semester");
        inputRegulerSectionCore.setValue("Reguler");
        inputRegulerSectionCore.setItems(RegulerListItem);
        inputSemesterSectionCore.setItems(SemesterListItem);
        UpdateTableInputView();
        UpdateTableHasilView();
        HasiKeputusanKategori();
    }

    //Untuk Menampilkan data kedalam TableView
    public void UpdateTableInputView() {
        try {
            containList = inputsDaoImpl.getALL();
            ObservableList<Inputs> ContainList = FXCollections.observableArrayList(containList);
            nimColumnInput.setCellValueFactory(new PropertyValueFactory<>("nim"));
            namaColumnInput.setCellValueFactory(new PropertyValueFactory<>("nama"));
            semesterColumnInput.setCellValueFactory(new PropertyValueFactory<>("semester"));
            regulerColumnInput.setCellValueFactory(new PropertyValueFactory<>("reguler"));
            ppjColumnInput.setCellValueFactory(new PropertyValueFactory<>("p_pembelajaran"));
            padColumnInput.setCellValueFactory(new PropertyValueFactory<>("p_administrasi"));
            prsColumnInput.setCellValueFactory(new PropertyValueFactory<>("p_sarana"));
            ptkColumnInput.setCellValueFactory(new PropertyValueFactory<>("p_perpustakaan"));
            pkmhColumnInput.setCellValueFactory(new PropertyValueFactory<>("p_kemahasiswaan"));

            tableInputView.setItems(ContainList);

            FilteredList<Inputs> filteredData = new FilteredList<>(ContainList, b -> true);

            pencarianBox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Inputs -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if (Inputs.getNim().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (Inputs.getNama().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
                SortedList<Inputs> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableInputView.comparatorProperty());

                tableInputView.setItems(sortedData);
            }
            );

        } catch (SQLException ex) {
            Logger.getLogger(CoreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    private void UpdateTableHasilView() {
        try {
            containerList = hasilProsesDaoImpl.getALL();
            ObservableList<HasilProses> ContainerList = FXCollections.observableArrayList(containerList);
            nimColumnProses.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getprosesnim()));
            namaColumnProses.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getprosesnama()));
            hasilColumnProses.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getprosesHasil()));
            keputusanColumnProses.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getproseskeputusan()));

            tableProsesView.setItems(ContainerList);

            FilteredList<HasilProses> filteredDatahasil = new FilteredList<>(ContainerList, b -> true);

            pencarianBox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredDatahasil.setPredicate(HasilProses -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if (HasilProses.getprosesnim().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (HasilProses.getprosesnama().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
                SortedList<HasilProses> sortedDatahasil = new SortedList<>(filteredDatahasil);
                sortedDatahasil.comparatorProperty().bind(tableProsesView.comparatorProperty());

                tableProsesView.setItems(sortedDatahasil);
            }
            );

        } catch (SQLException ex) {
            Logger.getLogger(CoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // untuk membersihkan kolom pengisian input data
    private void clearField() {
        inputNamaSectionCore.setText("");
        inputNimSectionCore.setText("");
        inputRegulerSectionCore.setValue("Reguler");
        inputSemesterSectionCore.setValue("Semester");
        inputPPJSectionCore.setText("");
        inputPADSectionCore.setText("");
        inputPRSSectionCore.setText("");
        inputPTKSectionCore.setText("");
        inputPKMHSectionCore.setText("");
        tidakMemuaskanBox.setText("");
        cukupMemuaskanBox.setText("");
        sangatMemuaskanBox.setText("");
    }

    //Polymerfism Proses Fuzzy
    public class ProsesFuzzy {

        // *Tahap 1* Inisialisasi Variabel Proses
        String respondenNim, respondenNama, formaterDecimal, hasilFam;

        // Variabel untuk menampung input
        double kondisi_ppj, kondisi_pad, kondisi_prs, kondisi_ptk, kondisi_pkmh,
                kondisi_total;

        // Variable untuk menampung nilai dari miu(u) variabel Pembelajaran FAM
        double u_pembelajaran_rendah, u_pembelajaran_normal, u_pembelajaran_tinggi;

        // Variable untuk menampung nilai dari miu(u) variabel Administrasi FAM
        double u_administrasi_buruk, u_administrasi_normal, u_administrasi_baik;

        // Variable untuk menampung nilai dari miu(u) variabel Prasarana FAM
        double u_sarana_buruk, u_sarana_biasa, u_sarana_baik;

        // Variable untuk menampung nilai dari miu(u) variabel Prasarana FAM
        double u_perpustakaan_buruk, u_perpustakaan_biasa, u_perpustakaan_baik;

        // Variable untuk menampung nilai dari miu(u) Variabel Kemahasiswaan FAM
        double u_mahasiswa_buruk, u_mahasiswa_biasa, u_mahasiswa_baik;

        // Variable untuk menampung nilai dari miu(u) variabel Variabel Semester FAM
        double u_train1, u_train2, u_train3, u_train4, u_train5;

        //pemendekan variabel (optional) biar mudah ngetiknya dan gak cape 
        PelayananPembelajaran pembelajaran;
        PelayananAdministrasi admiistrasi;
        PelayananPrasarana prasarana;
        PelayananPerpustakaan perpus;
        PelayananKemahasiswaan kemaha;
        TrainingSemester latihan;

        // *Tahap 2* Rumus penentuan fungsi keanggotaan untuk variable fungsi keangotaan Pelayanan Pembelajaran (PPJ).
        class PelayananPembelajaran {

            double data_PPJ;

            public double Kurang() {
                if (data_PPJ < 30) {
                    return 1;
                } else if ((data_PPJ > 30) && (data_PPJ < 60)) {
                    return (60.0 - data_PPJ) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Cukup() {
                if ((data_PPJ > 30) && (data_PPJ < 60)) {
                    return (data_PPJ - 30.0) / 30.0;
                } else if ((data_PPJ > 60) && (data_PPJ < 90)) {
                    return (90.0 - data_PPJ) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Puas() {
                if (data_PPJ >= 90) {
                    return 1;
                } else if ((data_PPJ >= 60) && (data_PPJ <= 90)) {
                    return (data_PPJ - 60.0) / 30.0;
                } else {
                    return 0;
                }
            }
        }

        //fungsi keanggotaan administrasi
        class PelayananAdministrasi {

            public double data_PAD;

            public double Kurang() {
                if (data_PAD < 30) {
                    return 1;
                } else if ((data_PAD >= 30) && (data_PAD <= 60)) {
                    return (60.0 - data_PAD) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Cukup() {
                if ((data_PAD >= 30) && (data_PAD <= 60)) {
                    return (data_PAD - 30.0) / 30.0;
                } else if ((data_PAD >= 60) && (data_PAD <= 90)) {
                    return (90.0 - data_PAD) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Puas() {
                if (data_PAD >= 90) {
                    return 1;
                } else if ((data_PAD >= 60) && (data_PAD <= 90)) {
                    return (data_PAD - 60.0) / 30.0;
                } else {
                    return 0;
                }
            }
        }

        // Rumus penentuan fungsi keanggotaan untuk variable fungsi keangotaan Pelayanan perpustakaan.
        class PelayananPrasarana {

            public double data_PRS;

            public double Kurang() {
                if (data_PRS < 30) {
                    return 1;
                } else if ((data_PRS >= 30) && (data_PRS <= 60)) {
                    return (60.0 - data_PRS) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Cukup() {
                if ((data_PRS >= 30) && (data_PRS <= 60)) {
                    return (data_PRS - 30.0) / 30.0;
                } else if ((data_PRS >= 60) && (data_PRS <= 90)) {
                    return (90.0 - data_PRS) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Puas() {
                if (data_PRS >= 90) {
                    return 1;
                } else if ((data_PRS >= 60) && (data_PRS <= 90)) {
                    return (data_PRS - 60.0) / 30.0;
                } else {
                    return 0;
                }
            }
        }

        // Rumus penentuan fungsi keanggotaan untuk variable fungsi keangotaan Pelayanan perpustakaan (PTK).
        class PelayananPerpustakaan {

            public double data_PTK;

            public double Kurang() {
                if (data_PTK < 30) {
                    return 1;
                } else if ((data_PTK >= 30) && (data_PTK <= 60)) {
                    return (60.0 - data_PTK) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Cukup() {
                if ((data_PTK >= 30) && (data_PTK <= 60)) {
                    return (data_PTK - 30.0) / 30.0;
                } else if ((data_PTK >= 60) && (data_PTK <= 90)) {
                    return (90.0 - data_PTK) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Puas() {
                if (data_PTK >= 90) {
                    return 1;
                } else if ((data_PTK >= 60) && (data_PTK <= 90)) {
                    return (data_PTK - 60.0) / 30.0;
                } else {
                    return 0;
                }
            }
        }

        // Rumus Penentuan Fungsi Keanggotaan untuk variabel Pelayanan Kemahasiswaan (PKMH).
        class PelayananKemahasiswaan {

            public double data_PKMH;

            public double Kurang() {
                if (data_PKMH < 30) {
                    return 1;
                } else if ((data_PKMH >= 30) && (data_PKMH <= 60)) {
                    return (60.0 - data_PKMH) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Cukup() {
                if ((data_PKMH >= 30) && (data_PKMH <= 60)) {
                    return (data_PKMH - 30.0) / 30.0;
                } else if ((data_PKMH >= 60) && (data_PKMH <= 90)) {
                    return (90.0 - data_PKMH) / 30.0;
                } else {
                    return 0;
                }
            }

            public double Puas() {
                if (data_PKMH >= 90) {
                    return 1;
                } else if ((data_PKMH >= 60) && (data_PKMH <= 90)) {
                    return (data_PKMH - 60.0) / 30.0;
                } else {
                    return 0;
                }
            }
        }

        //Rumus Penghitungan Fungsi Keanggotaan menggunakan grafik bahu. (Digunakan untuk mendapatkan nilai dari Matrix B ". 
        class TrainingSemester {

            public double data_SMST;

            public double TM() {
                if (data_SMST <= 100) {
                    return 1;
                } else if ((data_SMST >= 00) && (data_SMST <= 175)) {
                    return (175.0 - data_SMST) / 75.0;
                } else {
                    return 0;
                }
            }

            public double KM() {
                if ((data_SMST >= 100) && (data_SMST <= 175)) {
                    return (data_SMST - 100.0) / 75.0;
                } else if ((data_SMST >= 175) && (data_SMST <= 250)) {
                    return (250.0 - data_SMST) / 75.0;
                } else {
                    return 0;
                }
            }

            public double CM() {
                if ((data_SMST >= 175) && (data_SMST <= 250)) {
                    return (data_SMST - 175.0) / 75.0;
                } else if ((data_SMST >= 250) && (data_SMST <= 325)) {
                    return (325.0 - data_SMST) / 75.0;
                } else {
                    return 0;
                }
            }

            public double LM() {
                if ((data_SMST >= 250) && (data_SMST <= 325)) {
                    return (data_SMST - 250.0) / 75.0;
                } else if ((data_SMST >= 325) && (data_SMST <= 400)) {
                    return (400.0 - data_SMST) / 75.0;
                } else {
                    return 0;
                }
            }

            public double SM() {

                if (data_SMST >= 400) {
                    return 1;
                } else if ((data_SMST >= 325) && (data_SMST <= 400)) {
                    return (data_SMST - 325.0) / 75.0;
                } else {
                    return 0;
                }
            }

        }

        // Deklarasi dan Inisialisasi prosedur kedalam variabel 
        public void comput_membership() {
            pembelajaran = new PelayananPembelajaran();
            admiistrasi = new PelayananAdministrasi();
            prasarana = new PelayananPrasarana();
            perpus = new PelayananPerpustakaan();
            kemaha = new PelayananKemahasiswaan();
            latihan = new TrainingSemester();

            pembelajaran.data_PPJ = kondisi_ppj;
            u_pembelajaran_rendah = pembelajaran.Kurang();
            u_pembelajaran_normal = pembelajaran.Cukup();
            u_pembelajaran_tinggi = pembelajaran.Puas();

            admiistrasi.data_PAD = kondisi_pad;
            u_administrasi_buruk = admiistrasi.Kurang();
            u_administrasi_normal = admiistrasi.Cukup();
            u_administrasi_baik = admiistrasi.Puas();

            prasarana.data_PRS = kondisi_prs;
            u_sarana_buruk = prasarana.Kurang();
            u_sarana_biasa = prasarana.Cukup();
            u_sarana_baik = prasarana.Puas();

            perpus.data_PTK = kondisi_ptk;
            u_perpustakaan_buruk = perpus.Kurang();
            u_perpustakaan_biasa = perpus.Cukup();
            u_perpustakaan_baik = perpus.Puas();

            kemaha.data_PKMH = kondisi_pkmh;
            u_mahasiswa_buruk = kemaha.Kurang();
            u_mahasiswa_biasa = kemaha.Cukup();
            u_mahasiswa_baik = kemaha.Puas();

            latihan.data_SMST = kondisi_total;
            u_train1 = latihan.TM();
            u_train2 = latihan.KM();
            u_train3 = latihan.CM();
            u_train4 = latihan.LM();
            u_train5 = latihan.SM();
        }

        /*Tahap 3*/
        // Rule Menentukan Matriks A dan B
        private void Apply_RuleFAM() {

            double uP[] = new double[5]; // inisialisasi fixed array untuk menampung rule fungsi keanggotaan yang akan di siapkan  
            double Bi[] = new double[5]; // inisialisasi fixed array untuk menampung rule corelation max product yang akan di siapkan

            double B1[] = new double[5]; // insisialisasi menampung matriks M1 fam
            double B2[] = new double[5]; // insisialisasi menampung matriks M2 fam
            double B3[] = new double[5]; // insisialisasi menampung matriks M3 fam
            double B4[] = new double[5]; // insisialisasi menampung matriks M4 fam
            double B5[] = new double[5]; // insisialisasi menampung matriks M5 fam
            double B6[] = new double[5]; // insisialisasi menampung matriks M6 fam
            double B7[] = new double[5]; // insisialisasi menampung matriks M7 fam

            // Untuk debug test Hasil Fuzzyfikasi
            System.out.println("\n"+u_pembelajaran_rendah +" "+ u_pembelajaran_normal +" "+ u_pembelajaran_tinggi);
            System.out.println(u_administrasi_buruk +" "+ u_administrasi_normal +" "+ u_administrasi_baik);
            System.out.println(u_sarana_buruk +" "+ u_sarana_biasa +" "+ u_sarana_baik);
            System.out.println(u_perpustakaan_buruk +" "+ u_perpustakaan_biasa +" "+ u_perpustakaan_baik);
            System.out.println(u_mahasiswa_buruk +" "+u_mahasiswa_biasa+" "+ u_mahasiswa_baik);


            //  uP (miu Pelayanan) mengandung Setiap hasil yang didapat dari fungsi keanggotaan nilai terbesar.
            uP[0] = Find_Max(u_pembelajaran_rendah, u_pembelajaran_normal, u_pembelajaran_tinggi, 0, 0,0,0);
            uP[1] = Find_Max(u_administrasi_buruk, u_administrasi_normal, u_administrasi_baik, 0, 0,0,0);
            uP[2] = Find_Max(u_sarana_buruk, u_sarana_biasa, u_sarana_baik, 0, 0,0,0);
            uP[3] = Find_Max(u_perpustakaan_buruk, u_perpustakaan_biasa, u_perpustakaan_baik, 0, 0,0,0);
            uP[4] = Find_Max(u_mahasiswa_buruk, u_mahasiswa_biasa, u_mahasiswa_baik, 0, 0,0,0);

            ArrayList<Double> alist = new ArrayList<>();
            alist.add(uP[0]);
            alist.add(uP[1]);
            alist.add(uP[2]);
            alist.add(uP[3]);
            alist.add(uP[4]);

            ArrayList<Double> blist = new ArrayList<>();
            blist.add(u_train1);
            blist.add(u_train2);
            blist.add(u_train3);
            blist.add(u_train4);
            blist.add(u_train5);


            /* Tahap 4 */
            // Mencari Corelation-Product Encoding M= At * B
            ArrayList<Double> MAtB = new ArrayList<>();
            for (int i = 0; i < alist.size(); i++) {
                for (int j = 0; j < blist.size(); j++) {
                    double MATB = alist.get(i) * blist.get(j);
                    MAtB.add(MATB);
                }
            }
            //Test debug output M= At * B
            for (Double mtx : MAtB) {
                if (MAtB.indexOf(mtx) % 5 == 0 && MAtB.indexOf(mtx) != 0) {
                    System.out.print("\n");
                } else if (MAtB.indexOf(mtx) % 5 == 0 && MAtB.indexOf(mtx) == 0) {
                    System.out.print("\n");
                }
                System.out.printf(mtx + " ");
            }
            /* Tahap 5 */
            // Suprimposing Rulse Fam
            // 7 Data sample testing :
            ArrayList<Double> DT_Test_M1 = new ArrayList<>();
            DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.4666666666666667); DT_Test_M1.add(0.5333333333333333);
            DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.3111111111111111); DT_Test_M1.add(0.3555555555555555);
            DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.23333333333333334); DT_Test_M1.add(0.26666666666666666);
            DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.3111111111111111); DT_Test_M1.add(0.3555555555555555);
            DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.0); DT_Test_M1.add(0.3111111111111111); DT_Test_M1.add(0.3555555555555555);

            ArrayList<Double> DT_Test_M2 = new ArrayList<>();
            DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.16666666666666666); DT_Test_M2.add(0.3333333333333333);
            DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.16666666666666666); DT_Test_M2.add(0.3333333333333333);
            DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.16666666666666666); DT_Test_M2.add(0.3333333333333333);
            DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.16666666666666666); DT_Test_M2.add(0.3333333333333333);
            DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.0); DT_Test_M2.add(0.16666666666666666); DT_Test_M2.add(0.3333333333333333);

            ArrayList<Double> DT_Test_M3 = new ArrayList<>();
            DT_Test_M3.add(0.0); DT_Test_M3.add(0.0); DT_Test_M3.add(0.8); DT_Test_M3.add(0.2); DT_Test_M3.add(0.0);
            DT_Test_M3.add(0.0); DT_Test_M3.add(0.0); DT_Test_M3.add(0.4); DT_Test_M3.add(0.1); DT_Test_M3.add(0.0);
            DT_Test_M3.add(0.0); DT_Test_M3.add(0.0); DT_Test_M3.add(0.6666666666666667); DT_Test_M3.add(0.16666666666666669); DT_Test_M3.add(0.0);
            DT_Test_M3.add(0.0); DT_Test_M3.add(0.0); DT_Test_M3.add(0.5333333333333333); DT_Test_M3.add(0.13333333333333333); DT_Test_M3.add(0.0);
            DT_Test_M3.add(0.0); DT_Test_M3.add(0.0); DT_Test_M3.add(0.6666666666666667); DT_Test_M3.add(0.16666666666666669); DT_Test_M3.add(0.0);

            ArrayList<Double> DT_Test_M4 = new ArrayList<>();
            DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(1.0);
            DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(1.0);
            DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(1.0);
            DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(1.0);
            DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(0.0); DT_Test_M4.add(1.0);

            ArrayList<Double> DT_Test_M5 = new ArrayList<>();
            DT_Test_M5.add(0.0); DT_Test_M5.add(0.0); DT_Test_M5.add(0.4444444444444445); DT_Test_M5.add(0.3888888888888889); DT_Test_M5.add(0.0);
            DT_Test_M5.add(0.0); DT_Test_M5.add(0.0); DT_Test_M5.add(0.3555555555555555); DT_Test_M5.add(0.3111111111111111); DT_Test_M5.add(0.0);
            DT_Test_M5.add(0.0); DT_Test_M5.add(0.0); DT_Test_M5.add(0.4444444444444445); DT_Test_M5.add(0.3888888888888889); DT_Test_M5.add(0.0);
            DT_Test_M5.add(0.0); DT_Test_M5.add(0.0); DT_Test_M5.add(0.4444444444444445); DT_Test_M5.add(0.3888888888888889); DT_Test_M5.add(0.0);
            DT_Test_M5.add(0.0); DT_Test_M5.add(0.0); DT_Test_M5.add(0.3555555555555555); DT_Test_M5.add(0.3111111111111111); DT_Test_M5.add(0.0);

            ArrayList<Double> DT_Test_M6 = new ArrayList<>();
            DT_Test_M6.add(0.0); DT_Test_M6.add(0.26666666666666666); DT_Test_M6.add(0.39999999999999997); DT_Test_M6.add(0.0); DT_Test_M6.add(0.0);
            DT_Test_M6.add(0.0); DT_Test_M6.add(0.4); DT_Test_M6.add(0.6); DT_Test_M6.add(0.0); DT_Test_M6.add(0.0);
            DT_Test_M6.add(0.0); DT_Test_M6.add(0.4); DT_Test_M6.add(0.6); DT_Test_M6.add(0.0); DT_Test_M6.add(0.0);
            DT_Test_M6.add(0.0); DT_Test_M6.add(0.4); DT_Test_M6.add(0.6); DT_Test_M6.add(0.0); DT_Test_M6.add(0.0);
            DT_Test_M6.add(0.0); DT_Test_M6.add(0.33333333333333337); DT_Test_M6.add(0.5); DT_Test_M6.add(0.0); DT_Test_M6.add(0.0);

            ArrayList<Double> DT_Test_M7 = new ArrayList<>();
            DT_Test_M7.add(0.0); DT_Test_M7.add(0.0); DT_Test_M7.add(0.3333333333333333); DT_Test_M7.add(0.6666666666666666); DT_Test_M7.add(0.0);
            DT_Test_M7.add(0.0); DT_Test_M7.add(0.0); DT_Test_M7.add(0.2777777777777778); DT_Test_M7.add(0.5555555555555556); DT_Test_M7.add(0.0);
            DT_Test_M7.add(0.0); DT_Test_M7.add(0.0); DT_Test_M7.add(0.3333333333333333); DT_Test_M7.add(0.6666666666666666); DT_Test_M7.add(0.0);
            DT_Test_M7.add(0.0); DT_Test_M7.add(0.0); DT_Test_M7.add(0.2777777777777778); DT_Test_M7.add(0.5555555555555556); DT_Test_M7.add(0.0);
            DT_Test_M7.add(0.0); DT_Test_M7.add(0.0); DT_Test_M7.add(0.3333333333333333); DT_Test_M7.add(0.6666666666666666); DT_Test_M7.add(0.0);


            // Mecari Max Product Composition M1
            B1[0] = Find_Max((alist.get(0) * DT_Test_M1.get(0)), (alist.get(1) * DT_Test_M1.get(5)), (alist.get(2) * DT_Test_M1.get(10)), (alist.get(3) * DT_Test_M1.get(15)), (alist.get(4) * DT_Test_M1.get(20)),0,0);
            B1[1] = Find_Max((alist.get(0) * DT_Test_M1.get(1)), (alist.get(1) * DT_Test_M1.get(6)), (alist.get(2) * DT_Test_M1.get(11)), (alist.get(3) * DT_Test_M1.get(16)), (alist.get(4) * DT_Test_M1.get(21)),0,0);
            B1[2] = Find_Max((alist.get(0) * DT_Test_M1.get(2)), (alist.get(1) * DT_Test_M1.get(7)), (alist.get(2) * DT_Test_M1.get(12)), (alist.get(3) * DT_Test_M1.get(17)), (alist.get(4) * DT_Test_M1.get(22)),0,0);
            B1[3] = Find_Max((alist.get(0) * DT_Test_M1.get(3)), (alist.get(1) * DT_Test_M1.get(8)), (alist.get(2) * DT_Test_M1.get(13)), (alist.get(3) * DT_Test_M1.get(18)), (alist.get(4) * DT_Test_M1.get(23)),0,0);
            B1[4] = Find_Max((alist.get(0) * DT_Test_M1.get(4)), (alist.get(1) * DT_Test_M1.get(9)), (alist.get(2) * DT_Test_M1.get(14)), (alist.get(3) * DT_Test_M1.get(19)), (alist.get(4) * DT_Test_M1.get(24)),0,0);


          // Mecari Max Product Composition M2
            B2[0] = Find_Max((alist.get(0) * DT_Test_M2.get(0)), (alist.get(1) * DT_Test_M2.get(5)), (alist.get(2) * DT_Test_M2.get(10)), (alist.get(3) * DT_Test_M2.get(15)), (alist.get(4) * DT_Test_M2.get(20)),0,0);
            B2[1] = Find_Max((alist.get(0) * DT_Test_M2.get(1)), (alist.get(1) * DT_Test_M2.get(6)), (alist.get(2) * DT_Test_M2.get(11)), (alist.get(3) * DT_Test_M2.get(16)), (alist.get(4) * DT_Test_M2.get(21)),0,0);
            B2[2] = Find_Max((alist.get(0) * DT_Test_M2.get(2)), (alist.get(1) * DT_Test_M2.get(7)), (alist.get(2) * DT_Test_M2.get(12)), (alist.get(3) * DT_Test_M2.get(17)), (alist.get(4) * DT_Test_M2.get(22)),0,0);
            B2[3] = Find_Max((alist.get(0) * DT_Test_M2.get(3)), (alist.get(1) * DT_Test_M2.get(8)), (alist.get(2) * DT_Test_M2.get(13)), (alist.get(3) * DT_Test_M2.get(18)), (alist.get(4) * DT_Test_M2.get(23)),0,0);
            B2[4] = Find_Max((alist.get(0) * DT_Test_M2.get(4)), (alist.get(1) * DT_Test_M2.get(9)), (alist.get(2) * DT_Test_M2.get(14)), (alist.get(3) * DT_Test_M2.get(19)), (alist.get(4) * DT_Test_M2.get(24)),0,0);

           // Mecari Max Product Composition M3
            B3[0] = Find_Max((alist.get(0) * DT_Test_M3.get(0)), (alist.get(1) * DT_Test_M3.get(5)), (alist.get(2) * DT_Test_M3.get(10)), (alist.get(3) * DT_Test_M1.get(15)), (alist.get(4) * DT_Test_M1.get(20)),0,0);
            B3[1] = Find_Max((alist.get(0) * DT_Test_M3.get(1)), (alist.get(1) * DT_Test_M3.get(6)), (alist.get(2) * DT_Test_M3.get(11)), (alist.get(3) * DT_Test_M1.get(16)), (alist.get(4) * DT_Test_M1.get(21)),0,0);
            B3[2] = Find_Max((alist.get(0) * DT_Test_M3.get(2)), (alist.get(1) * DT_Test_M3.get(7)), (alist.get(2) * DT_Test_M3.get(12)), (alist.get(3) * DT_Test_M1.get(17)), (alist.get(4) * DT_Test_M1.get(22)),0,0);
            B3[3] = Find_Max((alist.get(0) * DT_Test_M3.get(3)), (alist.get(1) * DT_Test_M3.get(8)), (alist.get(2) * DT_Test_M3.get(13)), (alist.get(3) * DT_Test_M1.get(18)), (alist.get(4) * DT_Test_M1.get(23)),0,0);
            B3[4] = Find_Max((alist.get(0) * DT_Test_M3.get(4)), (alist.get(1) * DT_Test_M3.get(9)), (alist.get(2) * DT_Test_M3.get(14)), (alist.get(3) * DT_Test_M1.get(19)), (alist.get(4) * DT_Test_M1.get(24)),0,0);

           // Mecari Max Product Composition M4
            B4[0] = Find_Max((alist.get(0) * DT_Test_M4.get(0)), (alist.get(1) * DT_Test_M4.get(5)), (alist.get(2) * DT_Test_M4.get(10)), (alist.get(3) * DT_Test_M4.get(15)), (alist.get(4) * DT_Test_M4.get(20)),0,0);
            B4[1] = Find_Max((alist.get(0) * DT_Test_M4.get(1)), (alist.get(1) * DT_Test_M4.get(6)), (alist.get(2) * DT_Test_M4.get(11)), (alist.get(3) * DT_Test_M4.get(16)), (alist.get(4) * DT_Test_M4.get(21)),0,0);
            B4[2] = Find_Max((alist.get(0) * DT_Test_M4.get(2)), (alist.get(1) * DT_Test_M4.get(7)), (alist.get(2) * DT_Test_M4.get(12)), (alist.get(3) * DT_Test_M4.get(17)), (alist.get(4) * DT_Test_M4.get(22)),0,0);
            B4[3] = Find_Max((alist.get(0) * DT_Test_M4.get(3)), (alist.get(1) * DT_Test_M4.get(8)), (alist.get(2) * DT_Test_M4.get(13)), (alist.get(3) * DT_Test_M4.get(18)), (alist.get(4) * DT_Test_M4.get(23)),0,0);
            B4[4] = Find_Max((alist.get(0) * DT_Test_M4.get(4)), (alist.get(1) * DT_Test_M4.get(9)), (alist.get(2) * DT_Test_M4.get(14)), (alist.get(3) * DT_Test_M4.get(19)), (alist.get(4) * DT_Test_M4.get(24)),0,0);

           // Mecari Max Product Composition M5
            B5[0] = Find_Max((alist.get(0) * DT_Test_M5.get(0)), (alist.get(1) * DT_Test_M5.get(5)), (alist.get(2) * DT_Test_M5.get(10)), (alist.get(3) * DT_Test_M5.get(15)), (alist.get(4) * DT_Test_M5.get(20)),0,0);
            B5[1] = Find_Max((alist.get(0) * DT_Test_M5.get(1)), (alist.get(1) * DT_Test_M5.get(6)), (alist.get(2) * DT_Test_M5.get(11)), (alist.get(3) * DT_Test_M5.get(16)), (alist.get(4) * DT_Test_M5.get(21)),0,0);
            B5[2] = Find_Max((alist.get(0) * DT_Test_M5.get(2)), (alist.get(1) * DT_Test_M5.get(7)), (alist.get(2) * DT_Test_M5.get(12)), (alist.get(3) * DT_Test_M5.get(17)), (alist.get(4) * DT_Test_M5.get(22)),0,0);
            B5[3] = Find_Max((alist.get(0) * DT_Test_M5.get(3)), (alist.get(1) * DT_Test_M5.get(8)), (alist.get(2) * DT_Test_M5.get(13)), (alist.get(3) * DT_Test_M5.get(18)), (alist.get(4) * DT_Test_M5.get(23)),0,0);
            B5[4] = Find_Max((alist.get(0) * DT_Test_M5.get(4)), (alist.get(1) * DT_Test_M5.get(9)), (alist.get(2) * DT_Test_M5.get(14)), (alist.get(3) * DT_Test_M5.get(19)), (alist.get(4) * DT_Test_M5.get(24)),0,0);

            // Mecari Max Product Composition M6
            B6[0] = Find_Max((alist.get(0) * DT_Test_M6.get(0)), (alist.get(1) * DT_Test_M6.get(5)), (alist.get(2) * DT_Test_M6.get(10)), (alist.get(3) * DT_Test_M6.get(15)), (alist.get(4) * DT_Test_M6.get(20)),0,0);
            B6[1] = Find_Max((alist.get(0) * DT_Test_M6.get(1)), (alist.get(1) * DT_Test_M6.get(6)), (alist.get(2) * DT_Test_M6.get(11)), (alist.get(3) * DT_Test_M6.get(16)), (alist.get(4) * DT_Test_M6.get(21)),0,0);
            B6[2] = Find_Max((alist.get(0) * DT_Test_M6.get(2)), (alist.get(1) * DT_Test_M6.get(7)), (alist.get(2) * DT_Test_M6.get(12)), (alist.get(3) * DT_Test_M6.get(17)), (alist.get(4) * DT_Test_M6.get(22)),0,0);
            B6[3] = Find_Max((alist.get(0) * DT_Test_M6.get(3)), (alist.get(1) * DT_Test_M6.get(8)), (alist.get(2) * DT_Test_M6.get(13)), (alist.get(3) * DT_Test_M6.get(18)), (alist.get(4) * DT_Test_M6.get(23)),0,0);
            B6[4] = Find_Max((alist.get(0) * DT_Test_M6.get(4)), (alist.get(1) * DT_Test_M6.get(9)), (alist.get(2) * DT_Test_M6.get(14)), (alist.get(3) * DT_Test_M6.get(19)), (alist.get(4) * DT_Test_M6.get(24)),0,0);

           // Mecari Max Product Composition M7
            B7[0] = Find_Max((alist.get(0) * DT_Test_M7.get(0)), (alist.get(1) * DT_Test_M7.get(5)), (alist.get(2) * DT_Test_M7.get(10)), (alist.get(3) * DT_Test_M7.get(15)), (alist.get(4) * DT_Test_M7.get(20)),0,0);
            B7[1] = Find_Max((alist.get(0) * DT_Test_M7.get(1)), (alist.get(1) * DT_Test_M7.get(6)), (alist.get(2) * DT_Test_M7.get(11)), (alist.get(3) * DT_Test_M7.get(16)), (alist.get(4) * DT_Test_M7.get(21)),0,0);
            B7[2] = Find_Max((alist.get(0) * DT_Test_M7.get(2)), (alist.get(1) * DT_Test_M7.get(7)), (alist.get(2) * DT_Test_M7.get(12)), (alist.get(3) * DT_Test_M7.get(17)), (alist.get(4) * DT_Test_M7.get(22)),0,0);
            B7[3] = Find_Max((alist.get(0) * DT_Test_M7.get(3)), (alist.get(1) * DT_Test_M7.get(8)), (alist.get(2) * DT_Test_M7.get(13)), (alist.get(3) * DT_Test_M7.get(18)), (alist.get(4) * DT_Test_M7.get(23)),0,0);
            B7[4] = Find_Max((alist.get(0) * DT_Test_M7.get(4)), (alist.get(1) * DT_Test_M7.get(9)), (alist.get(2) * DT_Test_M7.get(14)), (alist.get(3) * DT_Test_M7.get(19)), (alist.get(4) * DT_Test_M7.get(24)),0,0);


            // Mencari Nilai Max dari tiap Matriks M1-M7
            ArrayList<Double> M1_MAX = new ArrayList<>();
            M1_MAX.add(B1[0]); M1_MAX.add(B1[1]); M1_MAX.add(B1[2]); M1_MAX.add(B1[3]); M1_MAX.add(B1[4]);
            ArrayList<Double> M2_MAX = new ArrayList<>();
            M2_MAX.add(B2[0]); M2_MAX.add(B2[1]); M2_MAX.add(B2[2]); M2_MAX.add(B2[3]); M2_MAX.add(B2[4]);
            ArrayList<Double> M3_MAX = new ArrayList<>();
            M3_MAX.add(B3[0]); M3_MAX.add(B3[1]); M3_MAX.add(B3[2]); M3_MAX.add(B3[3]); M3_MAX.add(B3[4]);
            ArrayList<Double> M4_MAX = new ArrayList<>();
            M4_MAX.add(B4[0]); M4_MAX.add(B4[1]); M4_MAX.add(B4[2]); M4_MAX.add(B4[3]); M4_MAX.add(B4[4]);
            ArrayList<Double> M5_MAX = new ArrayList<>();
            M5_MAX.add(B5[0]); M5_MAX.add(B5[1]); M5_MAX.add(B5[2]); M5_MAX.add(B5[3]); M5_MAX.add(B5[4]);
            ArrayList<Double> M6_MAX = new ArrayList<>();
            M6_MAX.add(B6[0]); M6_MAX.add(B6[1]); M6_MAX.add(B6[2]); M6_MAX.add(B6[3]); M6_MAX.add(B6[4]);
            ArrayList<Double> M7_MAX = new ArrayList<>();
            M7_MAX.add(B7[0]); M7_MAX.add(B7[1]); M7_MAX.add(B7[2]); M7_MAX.add(B7[3]); M7_MAX.add(B7[4]);


            // test debug output Matriks M
            System.out.println("\n\nB1 : "+M1_MAX);
            System.out.println("B2 : "+M2_MAX);
            System.out.println("B3 : "+M3_MAX);
            System.out.println("B4 : "+M4_MAX);
            System.out.println("B5 : "+M5_MAX);
            System.out.println("B6 : "+M6_MAX);
            System.out.println("B7 : "+M7_MAX);

            /*Tahap 6*/
            // Mecari Max Product Composition (MPC)  B1’ = Anew * M1 Anew = alist
            Bi[0] = Find_Max(M1_MAX.get(0), M2_MAX.get(0), M3_MAX.get(0), M4_MAX.get(0), M5_MAX.get(0), M6_MAX.get(0), M7_MAX.get(0));
            Bi[1] = Find_Max(M1_MAX.get(1), M2_MAX.get(1), M3_MAX.get(1), M4_MAX.get(1), M5_MAX.get(1), M6_MAX.get(1), M7_MAX.get(1));
            Bi[2] = Find_Max(M1_MAX.get(2), M2_MAX.get(2), M3_MAX.get(2), M4_MAX.get(2), M5_MAX.get(2), M6_MAX.get(2), M7_MAX.get(2));
            Bi[3] = Find_Max(M1_MAX.get(3), M2_MAX.get(3), M3_MAX.get(3), M4_MAX.get(3), M5_MAX.get(3), M6_MAX.get(3), M7_MAX.get(3));
            Bi[4] = Find_Max(M1_MAX.get(4), M2_MAX.get(4), M3_MAX.get(4), M4_MAX.get(4), M5_MAX.get(4), M6_MAX.get(4), M7_MAX.get(4));

            ArrayList<Double> MPC = new ArrayList<>();
            MPC.add(Bi[0]);
            MPC.add(Bi[1]);
            MPC.add(Bi[2]);
            MPC.add(Bi[3]);
            MPC.add(Bi[4]);

            System.out.println("\n\n Deret A    : " + alist);
            System.out.println(" Deret B    :  " + blist);
            System.out.println(" Deret MAtB : " + MAtB);
            System.out.println(" Deret MPC  :  " + MPC);

            // Mencari nilai max dari B
            double BMAX = Find_Max(Bi[0], Bi[1], Bi[2], Bi[3], Bi[4],0,0);

            /*Defuzzy Tahap* 4*/
            DecimalFormat df = new DecimalFormat("0.00");


            // Winner Take All (WTA)
            double WTA = (BMAX * 100);

            // Weight Average(WAG)
            double Jumlah_WAG = (MPC.get(0) * kondisi_ppj) + (MPC.get(1) * kondisi_pad) + (MPC.get(2) * kondisi_prs) + (MPC.get(3) * kondisi_ptk) + (MPC.get(4) * kondisi_pkmh);
            double bagi_WAG =(MPC.get(0) + MPC.get(1) + MPC.get(2) + MPC.get(3) + MPC.get(4));
            double WAG = Jumlah_WAG / bagi_WAG ; // mengitung WAG

            // untuk memformat bentuk dari decimal tidak melebihi 2 angka setelah  (,)
            formaterDecimal = df.format(Jumlah_WAG);
            formaterDecimal = df.format(bagi_WAG);
            formaterDecimal = df.format(WAG);

            System.out.println("\n\n Hasil 1 WAG : "+Jumlah_WAG);
            System.out.println(" Hasil 2 WAG : "+(MPC.get(0) + MPC.get(1) + MPC.get(2) + MPC.get(3) + MPC.get(4)));

            System.out.println("\n Bi:  " + MPC + "\n BMax : " + BMAX);
            System.out.println(" WTA:  " + WTA + "\n WAG : " + WAG);

            if (WAG >= 0 && WAG <= 45) {
                hasilFam = "Tidak Memuaskan";
            } else if (WAG >= 46 && WAG <= 79) {
                hasilFam = "Cukup Memuaskan";
            } else if (WAG >= 80 && WAG <= 100) {
                hasilFam = "Sangat Memuaskan";
            } else {
                hasilFam = "Sangat Memuaskan";
            }
            System.out.println("\n" + hasilFam);
        }

        private void SaveProses() {
            try {
                HasilProses hsp = new HasilProses();

                double dcf = Double.parseDouble(formaterDecimal);

                hsp.setProsesHasil(dcf);
                hsp.setproseskeputusan(hasilFam);
                hsp.setprosesnim(respondenNim);

                int result = hasilProsesDaoImpl.inputhasil(hsp);

                System.out.println(result + "Simpan berhasil");
                //InfoBox("Simpan Berhasil", null, "Save Confirmation");
                //UpdateTableHasilView();
                clearField();
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
        }

        // Prosedur untuk mencari nilai terbesar dari matriks miu variabel FAM
        public double Find_Max(double a, double b, double c, double d, double e, double f,double g) {
            double result;
            List<Double> list = new ArrayList<Double>();

            list.add(a);
            list.add(b);
            list.add(c);
            list.add(d);
            list.add(e);

            //Collections.sort(list);
            result = Collections.max(list);
            //System.out.println("\n MAX input :" + result);

            return result;

        }

    }

    private void HasiKeputusanKategori() {

        try {

            DatabaseConnection connections = new DatabaseConnection();
            Connection connectDB = connections.getConnection();

            Statement stmt = connectDB.createStatement();

            String CountTM = "SELECT A.keputusan,(SELECT COUNT(*) FROM tb_hasilpenilaian_fam WHERE keputusan = 'TIDAK MEMUASKAN') AS TM, (SELECT COUNT(*) FROM tb_hasilpenilaian_fam WHERE keputusan = 'CUKUP MEMUASKAN') AS CM, (SELECT COUNT(*) FROM tb_hasilpenilaian_fam WHERE keputusan = 'SANGAT MEMUASKAN') AS SM FROM (SELECT DISTINCT keputusan FROM tb_hasilpenilaian_fam) A";

            ResultSet rs = stmt.executeQuery(CountTM);

            while (rs.next()) {
                String kpsTM = rs.getString("TM");
                tidakMemuaskanBox.setText(kpsTM);
                String kpsCM = rs.getString("CM");
                cukupMemuaskanBox.setText(kpsCM);
                String kpsSM = rs.getString("SM");
                sangatMemuaskanBox.setText(kpsSM);

            }

        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }

}
