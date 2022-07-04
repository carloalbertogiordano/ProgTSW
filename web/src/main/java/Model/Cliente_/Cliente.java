package Model.Cliente_;

public class Cliente {
    private String Mail, Pass, Nickname, Via, Provincia, Tel;
    Boolean Administrator;
    public Cliente(String mail, String pass, String nickname, String tel, String via, String provincia, Boolean administrator) {
        Mail = mail;
        Pass = pass;
        Nickname = nickname;
        Via = via;
        Provincia = provincia;
        Tel = tel;
        Administrator = administrator;
    }

    public Cliente(String mail, String nickname, Boolean administrator){
        this.Mail = mail;
        this.Nickname = nickname;
        this.Administrator = administrator;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getVia() {
        return Via;
    }

    public void setVia(String via) {
        Via = via;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public Boolean isAdministrator() {
        return Administrator;
    }

}
