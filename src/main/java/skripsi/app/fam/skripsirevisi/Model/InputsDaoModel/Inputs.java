package skripsi.app.fam.skripsirevisi.Model.InputsDaoModel;

/**
 *
 * @author qifli
 */

public class Inputs {

    // Variable tb_penilaian tipe int
    private int p_pembelajaran,
            p_administrasi,
            p_sarana,
            p_perpustakaan,
            p_kemahasiswaan;
    
    // variable tb_penilaian tipe String
    private String nim, nama, semester,
            reguler;

    // Constructor untuk OOP DAO
    public Inputs(String nim, String nama, String semester, String reguler, int p_pembelajaran, int p_administrasi, int p_sarana, int p_perpustakaan, int p_kemahasiswaan) {

        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.reguler = reguler;
        this.p_pembelajaran = p_pembelajaran;
        this.p_administrasi = p_administrasi;
        this.p_sarana = p_sarana;
        this.p_perpustakaan = p_perpustakaan;
        this.p_kemahasiswaan = p_kemahasiswaan;

    }

    //Empty Construc untuk encapsulasi
    public Inputs() {

    }

 

    public int getP_pembelajaran() {
        return p_pembelajaran;
    }

    public void setP_pembelajaran(int p_pembelajaran) {
        this.p_pembelajaran = p_pembelajaran;
    }

    public int getP_administrasi() {
        return p_administrasi;
    }

    public void setP_administrasi(int p_administrasi) {
        this.p_administrasi = p_administrasi;
    }

   public void setP_sarana(int p_sarana) {
        this.p_sarana = p_sarana;
    }

    public int getP_perpustakaan() {
        return p_perpustakaan;
    }

    public void setP_perpustakaan(int p_perpustakaan) {
        this.p_perpustakaan = p_perpustakaan;
    }

    public void setP_kemahasiswaan(int p_kemahasiswaan) {
        this.p_kemahasiswaan = p_kemahasiswaan;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getReguler() {
        return reguler;
    }

    public void setReguler(String reguler) {
        this.reguler = reguler;
    }

    public int getP_kemahasiswaan() {
        return p_kemahasiswaan;
    }

    public int getP_sarana() {
        return p_sarana;
    }
/*
    // To String untuk testing manual 
    @Override
    public String toString() {
        return "Inputs{" + "id_penilaian=" + id_penilaian + ", semester=" + semester + ", p_pembelajaran=" + p_pembelajaran + ", p_administrasi=" + p_administrasi + ", p_sarana=" + p_sarana + ", p_perpustakaan=" + p_perpustakaan + ", p_kemahasiswaan=" + p_kemahasiswaan + ", nim=" + nim + ", nama=" + nama + ", reguler=" + reguler + '}';
    }
*/
}
