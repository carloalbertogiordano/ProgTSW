package Model.Carrello_;

import Model.Cliente_.Cliente;
import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {

    public int createCarrello() throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Carrello (totale) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        int idCarrello = rs.getInt(1);
        return idCarrello;
    }
    public int doRetriveCarrelloEvasoCodByMailCLiente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true;");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        int codice = -1;
        while(rs.next()){
            codice = rs.getInt(1);
        }
        return codice;
    }

    public double doRetrivePrezzoByIdCarrello(int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Totale FROM Carrello WHERE Cod = ?;");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();
        double totale = -1;
        while(rs.next()){
            totale = rs.getInt(1);
        }
        return totale;
    }

    private int getComporreQuantita(int id, int carrelloCod) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Quantita FROM Comporre WHERE PezzoId = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, id);
        pdstmt.setInt(2, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();
        int quantita = -1;
        while(rs.next()){
            quantita = rs.getInt(1);
        }
        return quantita;
    }

    public void doUpdateQuantitaRichiestaById(int id, int carrelloCod, int quantita) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Comporre SET Quantita = ? WHERE PezzoID = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, quantita);
        pdstmt.setInt(2, id);
        pdstmt.setInt(3, carrelloCod);
        pdstmt.executeUpdate();
    }

    public Carrello doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod = (SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true);");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        ArrayList<Integer> prodotti = new ArrayList<Integer>();
        //ArrayList<Integer> richiesti = new ArrayList<Integer>();
        while(rs.next()){
            Integer i = rs.getInt(1);
            //Integer quantita = rs.getInt(2);
            prodotti.add(i);
            //richiesti.add(quantita);
        }
        int carrelloCod = doRetriveCarrelloEvasoCodByMailCLiente(mail);
        double totaleCarrello = doRetrivePrezzoByIdCarrello(carrelloCod);
        List<Prodotto> listProdotti = new ArrayList<Prodotto>();
        listProdotti = Prodotto.doRetriveByIdLis(prodotti);
        for (Prodotto prodotto : listProdotti) {
            int quantitaRichiesta = getComporreQuantita(prodotto.getID(), carrelloCod);
            prodotto.setQuantità(quantitaRichiesta);
        }
        Carrello carrello = new Carrello(listProdotti, carrelloCod, totaleCarrello);
        return carrello;
    }

    public void updateCarrello(Carrello carrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("DELETE FROM Comporre WHERE CarrelloCod = ?");
        pdstmt.setInt(1, carrello.getCarrelloCod());
        pdstmt.executeUpdate();
        for(int i = 0; i < carrello.getCarrello().size(); i++){
            addCartDB(carrello.getCarrello().get(i).getID(), carrello.getCarrelloCod(), carrello.getCarrello().get(i).getQuantità());
        }
    }
    public void addCartDB(int idPezzo, int idCarrello, int quantity) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Comporre VALUES (?, ?, ?)");
        pdstmt.setInt(1, idPezzo);
        pdstmt.setInt(2, idCarrello);
        pdstmt.setInt(3, quantity);
        System.out.println("Id Pezzo: " + idPezzo +", Id carrello: " + idCarrello + ", quantità: " + quantity);
        pdstmt.executeUpdate();
    }

    public void updateTotalCarrello(Carrello carrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Carrello SET Totale = ? WHERE Cod = ?");
        pdstmt.setDouble(1, carrello.calculateTotal());
        pdstmt.setInt(2, carrello.getCarrelloCod());
        pdstmt.executeUpdate();
    }

    //Setta a vero l'attributo Evaso nella tabella Ordine
    public void setOrdineEvaso(int codCarrello, String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Ordine SET Evaso = ? WHERE ClienteMail = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, 1);
        pdstmt.setString(2, mail);
        pdstmt.setInt(3, codCarrello);
        pdstmt.executeUpdate();
    }

    public void setIndirizzoOrdine(String via, String provincia, String citta, int cap, String mail, int codCarrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Ordine SET Via = ?, Provincia = ?, Citta = ?, Cap = ? WHERE ClienteMail = ? AND CarrelloCod = ?");
        pdstmt.setString(1,via);
        pdstmt.setString(2,provincia);
        pdstmt.setString(3,citta);
        pdstmt.setInt(4, cap);
        pdstmt.setString(5, mail);
        pdstmt.setInt(6,codCarrello);
        pdstmt.executeUpdate();
    }

    public void createNewOrdine(Cliente c, int idCarrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Ordine VALUES (?, ?, ?, ?, ?, ?, ?)");
        pdstmt.setString(1, c.getMail());
        pdstmt.setInt(2, idCarrello);
        pdstmt.setInt(3, 0);
        pdstmt.setString(4, c.getVia());
        pdstmt.setString(5,c.getProvincia());
        pdstmt.setString(6, c.getCitta());
        pdstmt.setInt(7, c.getCap());
        pdstmt.executeUpdate();
    }

    //Ritorna tutto lo storico dei carrelli sottoforma di carrello
    public static ArrayList<Carrello> doRetriveStorico(String mail) throws SQLException {
        ArrayList<Integer> storicoCarrelli = doRetriveStoricoCarrelliIdEvasiByMail(mail);
        ArrayList<Carrello> listaCarrelli = new ArrayList<>();
        CarrelloDAO cDAO = new CarrelloDAO();

        //Recupera tutti i carrelli
        for(Integer i : storicoCarrelli){
            listaCarrelli.add(cDAO.doRetriveCarrelloById(i));
            System.out.println("Cod: "+i+ " Size"+listaCarrelli.size());
        }
        //Setta la lista di prodotti nel carrello
        for(Carrello c : listaCarrelli){
            c.setCarrello(cDAO.doRetriveProdottiByIdCarrello(c.getCarrelloCod()));
        }
        //Recupera la quantità di ogni prodotto nel carrello
        for(Carrello c : listaCarrelli){
            for(Prodotto p : c.getCarrello()){
                p.setQuantità(cDAO.getComporreQuantita(p.getID(), c.getCarrelloCod()));
            }
        }

        System.out.println("Size Ordini: "+listaCarrelli.size());

        if(listaCarrelli.size() == 0)
            return null;
        return listaCarrelli;
        //return listaCarrelli.size() == 0 ? null:listaCarrelli ;
    }

    private List<Prodotto> doRetriveProdottiByIdCarrello(int carrelloCod) throws SQLException {
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        ArrayList<Integer> listaCodiciProdotti = new ArrayList<Integer>();
        CarrelloDAO cDAO = new CarrelloDAO();

        listaCodiciProdotti = cDAO.doRetriveListaCodiciProdotti(carrelloCod);
        listaProdotti = ProdottoDAO.doRetriveListaIdProdotti(listaCodiciProdotti);

        return listaProdotti;
    }

    private ArrayList<Integer> doRetriveListaCodiciProdotti(int carrelloCod) throws SQLException {
        ArrayList<Integer> codProdotti = new ArrayList<Integer>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Comporre WHERE CarrelloCod = ?");
        pdstmt.setInt(1, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();

        while(rs.next()){
            codProdotti.add(rs.getInt(1));
        }
        return codProdotti;
    }

    public Carrello doRetriveCarrelloById(Integer id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Cod,Totale FROM Carrello WHERE Cod = ?");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();

        if(rs.next()){
            return new Carrello(rs.getInt(1), rs.getDouble(2));
        }
        return null;
    }

    //Recupera tutti gli id dei carrelli che sono stati evasi dalla mail
    private static ArrayList<Integer> doRetriveStoricoCarrelliIdEvasiByMail(String mail) throws SQLException {
        ArrayList<Integer> codCarrelli = new ArrayList<Integer>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? and Evaso = true");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();

        while(rs.next()){
            Integer i = rs.getInt(1);
            codCarrelli.add(i);
        }
        return codCarrelli;
    }

}
