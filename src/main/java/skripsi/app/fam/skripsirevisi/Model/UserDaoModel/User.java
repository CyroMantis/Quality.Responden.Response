package skripsi.app.fam.skripsirevisi.Model.UserDaoModel;

/**
 *
 * @author qifli
 */
public class User {

   // private int id;
    private String nidn,
            nama_depan,
            nama_belakang,
            nama_lengkap,
            email,
            no_telp,
            dtl_username,
            dtl_password;

    
    public User(String nidn, String nama_depan, String nama_belakang, String nama_lengkap, String email, String no_telp) {
        //this.id = id;
        this.nidn = nidn;
        this.nama_depan = nama_depan;
        this.nama_belakang = nama_belakang;
        this.nama_lengkap = nama_lengkap;
        this.email = email;
        this.no_telp = no_telp;
    }

    public User(String dtl_username, String dtl_password,String nidn) {
        this.dtl_username = dtl_username;
        this.dtl_password = dtl_password;
        this.nidn = nidn;
    }

    public User() {

    }


    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNama_depan() {
        return nama_depan;
    }

    public void setNama_depan(String nama_depan) {
        this.nama_depan = nama_depan;
    }

    public String getNama_belakang() {
        return nama_belakang;
    }

    public void setNama_belakang(String nama_belakang) {
        this.nama_belakang = nama_belakang;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getDtl_username() {
        return dtl_username;
    }

    public void setDtl_username(String dtl_username) {
        this.dtl_username = dtl_username;
    }

    public String getDtl_password() {
        return dtl_password;
    }

    public void setDtl_password(String dtl_password) {
        this.dtl_password = dtl_password;
    }


}
