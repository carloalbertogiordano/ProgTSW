package Model.Archiviazione_;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.ProdottoDAO;

import java.sql.SQLException;

public abstract class ArchivioDatiDAO {

    public void UploadGeneric(ArchivioDati a) throws SQLException {
        ProdottoDAO.Upload(a.getMarca(), a.getModello(), a.getPrezzo(), a.getQuantita(), null, a.getTipo(), null, null, null, null, null, a.getMBs(), null, null, null, null, a.getUrl(), a.getDescrizione());
    }

    public void Upload(Ssd s) throws SQLException {
        UploadGeneric(s);
    }

    public void Upload(Hdd h) throws SQLException {
        UploadGeneric(h);
    }


}
