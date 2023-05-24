package skripsi.app.fam.skripsirevisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import skripsi.app.fam.skripsirevisi.Model.HasilProsesModel.HasilProses;
import skripsi.app.fam.skripsirevisi.Model.HasilProsesModel.HasilProsesDaoImpl;
import skripsi.app.fam.skripsirevisi.Model.InputsDaoModel.Inputs;
import skripsi.app.fam.skripsirevisi.Model.InputsDaoModel.InputsDaoImpl;
import skripsi.app.fam.skripsirevisi.Model.UserDaoModel.User;
import skripsi.app.fam.skripsirevisi.Model.UserDaoModel.UserDaoImpl;
import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestModel
{
    DatabaseConnection connections = new DatabaseConnection();
    InputsDaoImpl IPI = new InputsDaoImpl();

    UserDaoImpl USDI =  new UserDaoImpl();

    Connection connectDB = connections.getConnection();

    @Test
    // User Add
    public void testModelAddUserAccount(){
        String ND= "Test";
        String NB= "Account";
        String NDN= "123456789";
        String USN= "top";
        String PASS= "121200";
        User USD = new User(NDN, ND, NB, ND +" "+ NB, "TestAccount@gmail.com", "123456");
        User USUP = new User(USN,PASS, NDN);
        try {
            connectDB.setAutoCommit(false);
            USDI.insertDetail(USD);
            USDI.insertAccount(USUP);

            Assertions.assertNotNull(USD);
            Assertions.assertNotNull(USUP);

        }catch (Exception exception){
            exception.getCause();
            exception.printStackTrace();
        }
    }
    @Test
    // Input Penilaian Add
    public void testModelAddPenilaian(){
        Inputs addPenilaian = new Inputs("18102551","Udik","8","B", 80, 75, 60, 90,90);
        try {
            connectDB.setAutoCommit(false);
            IPI.inputs(addPenilaian);
            assertNotNull(addPenilaian);
            assertEquals(3, IPI.getALL().size());
            // test data yang di input
            Inputs inputs = IPI.get(addPenilaian.getNim(), addPenilaian.getNama());
            Assertions.assertEquals(addPenilaian.getNim(), inputs.getNim());
            Assertions.assertEquals(addPenilaian.getNama(), inputs.getNama());
            Assertions.assertEquals(addPenilaian.getSemester(), inputs.getSemester());
            Assertions.assertEquals(addPenilaian.getReguler(), inputs.getReguler());
            Assertions.assertEquals(addPenilaian.getP_pembelajaran(), inputs.getP_pembelajaran());
            Assertions.assertEquals(addPenilaian.getP_administrasi(), inputs.getP_administrasi());
            Assertions.assertEquals(addPenilaian.getP_sarana(), inputs.getP_sarana());
            Assertions.assertEquals(addPenilaian.getP_perpustakaan(), inputs.getP_perpustakaan());
            Assertions.assertEquals(addPenilaian.getP_kemahasiswaan(), inputs.getP_kemahasiswaan());
        }catch (Exception exception){
            exception.getCause();
            exception.printStackTrace();
        }finally {
            try {
                connectDB.rollback();
                connectDB.setAutoCommit(true);
            }catch (Exception exception){
                exception.getCause();
                exception.printStackTrace();
            }
        }
    }

    @Test
    // Input Get
    public void testModelinputget() throws SQLException {
        Inputs IP1 = IPI.get("1810400156","Dwi");
        //test jika data tersedia
        Assertions.assertNotNull(IP1);
        //Test jika data tidak tersedia
        //Assertions.assertNull(IP1);
        //Standar Sekenario
        Inputs IP2 = IPI.get("1810811","Test");
        assertNotNull(IPI);
        Assertions.assertEquals("1810811", IP2.getNim());
        Assertions.assertEquals("Test", IP2.getNama());
        Assertions.assertEquals("12", IP2.getSemester());
        Assertions.assertEquals("B", IP2.getReguler());
        Assertions.assertEquals(80, IP2.getP_pembelajaran());
        Assertions.assertEquals(75, IP2.getP_administrasi());
        Assertions.assertEquals(60, IP2.getP_sarana());
        Assertions.assertEquals(55, IP2.getP_perpustakaan());
        Assertions.assertEquals(40, IP2.getP_kemahasiswaan());
    }

    @Test
    //Input GetAll
    public void testModelinputgetAll() throws SQLException {
        ArrayList <Inputs> listInputs = (ArrayList<Inputs>) IPI.getALL();
        Assertions.assertNotNull(listInputs);
        Assertions.assertEquals(3, listInputs.size());
    }
    @Test
    //Input edit
    public void testModelinputEdit(){
        Inputs inputs = new Inputs();
       try {
            connectDB.setAutoCommit(false);
            // test edit data
            String Enama = "Test";
            String Esmester = "12";
            String Ereguler = "B";
            int Epembelajaran = 85;
            int Eadministrasi = 60;
            int Esarana = 75;
            int Eperpustakaan = 50;
            int Ekemahasiswaan = 30;

            inputs.setNama(Enama);
            inputs.setSemester(Esmester);
            inputs.setReguler(Ereguler);
            inputs.setP_pembelajaran(Epembelajaran);
            inputs.setP_administrasi(Eadministrasi);
            inputs.setP_sarana(Esarana);
            inputs.setP_perpustakaan(Eperpustakaan);
            inputs.setP_kemahasiswaan(Ekemahasiswaan);
            inputs.setNim("1810811");
            IPI.update(inputs);

            Inputs inputS = IPI.get("1810811","Test");

            Assertions.assertEquals(Enama, inputS.getNama());
            Assertions.assertEquals(Esmester, inputS.getSemester());
            Assertions.assertEquals(Ereguler, inputS.getReguler());
            Assertions.assertEquals(Epembelajaran, inputS.getP_pembelajaran());
            Assertions.assertEquals(Eadministrasi, inputS.getP_administrasi());
            Assertions.assertEquals(Esarana, inputS.getP_sarana());
            Assertions.assertEquals(Eperpustakaan, inputS.getP_perpustakaan());
            Assertions.assertEquals(Ekemahasiswaan, inputS.getP_kemahasiswaan());

        }catch (Exception exception){
            exception.getCause();
            exception.printStackTrace();
        }finally {
            try {
                connectDB.rollback();
                connectDB.setAutoCommit(true);
            }catch (Exception exception){
                exception.getCause();
                exception.printStackTrace();
            }
        }
    }

    @Test
    // Input Delete
    public void testModelInputDelete (){
        Inputs addPenilaian = new Inputs("40858","Gloo","7","B", 80, 75, 60, 90,90);
        try {
            connectDB.setAutoCommit(false);
            IPI.inputs(addPenilaian);

            Inputs inputS = IPI.get("40858","Gloo");
            IPI.delete(inputS);
            Assertions.assertEquals(2, IPI.getALL().size());
            Inputs inputs =IPI.get(addPenilaian.getNim(),addPenilaian.getNama());
            Assertions.assertNull(inputs);

        }catch (Exception exception){
            exception.getCause();
            exception.printStackTrace();
        }finally {
            try {
                connectDB.rollback();
                connectDB.setAutoCommit(true);
            }catch (Exception exception){
                exception.getCause();
                exception.printStackTrace();
            }
        }
    }

    @Test
    // Add Hasil Proses
    public void inputhasiltest(){
        HasilProsesDaoImpl HSPDI = new HasilProsesDaoImpl();
        HasilProses HSP = new HasilProses("1810400156","Dwi",66.94,"Cukup Memuaskan");
        Connection connectDB = connections.getConnection();
        try {
            connectDB.setAutoCommit(false);
            HSPDI.inputhasil(HSP);
            Assertions.assertNotNull(HSP);
            //Assertions.assertTrue(2 < HSP.getId());
        }catch (Exception exception){
            exception.getCause();
            exception.printStackTrace();
        }
    }
}