package Model.Carrello_;

import Model.CATALOGO_.Catalogo;
import Model.Cliente_.Cliente;
import Model.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.info;

public class Carrello {
    private List<Prodotto> carrello;
    private int carrelloCod;
    private double prezzo;

    public Carrello(int carrelloCod, double prezzo) {
        this.carrelloCod = carrelloCod;
        this.prezzo = prezzo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Carrello(){
        carrello=new ArrayList();
    }
    public Carrello(List<Prodotto> lista, int codice, double prezzo){
        this.carrello = lista;
        this.carrelloCod = codice;
        this.prezzo = prezzo;
    }

    public List<Prodotto> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Prodotto> carrello) {
        this.carrello = carrello;
    }

    public int getCarrelloCod() {
        return carrelloCod;
    }

    public void setCarrelloCod(int carrelloCod) {
        this.carrelloCod = carrelloCod;
    }

    //Controlla se la quantità richiesta è compatibile con la quantità totale disponibile nel DB
    public void doCheckList(Catalogo catalogo) throws SQLException {
        ArrayList<Prodotto> list=new ArrayList<Prodotto>();
        for(Prodotto p : carrello){
            int quantitaDisponible = catalogo.getQuantità(p.getID());
            int quantitaRichiesta = p.getQuantita();

            if(quantitaDisponible == 0){
                list.add(p);
            }
            else if(quantitaRichiesta > quantitaDisponible){
                p.setQuantita(quantitaDisponible);
            }
        }
        for(Prodotto p:list)
            carrello.remove(p);
    }

    //Esegue la join tra questo carrello e un altro carrello (Del db in questo caso)
    //elimina prima i duplicati dal carrello nel DB mettendoli in questo (sommando le quantità dei prodotti)
    //Poi aggiunge i prodotti rimanenti nel carrello del DB in questo
    public void joinCarrelli(Carrello carrelloDB){
        Carrello carrelloSession = this;
        if(carrelloSession.getCarrello()!=null)
            for(int i = 0; i < carrelloSession.getCarrello().size(); i++){
                if(carrelloDB.getCarrello()!=null)
                    for(int j = 0; j < carrelloDB.getCarrello().size(); j++){
                        if(carrelloSession.getCarrello().get(i).getID() == carrelloDB.getCarrello().get(j).getID()){
                            int quantita = carrelloSession.getCarrello().get(i).getQuantita() +  carrelloDB.getCarrello().get(j).getQuantita();
                            carrelloSession.getCarrello().get(i).setQuantita(quantita);
                            carrelloDB.getCarrello().remove(j);
                        }
                    }
            }
        for(Prodotto p : carrelloDB.getCarrello()){
            carrelloSession.getCarrello().add(p);
        }
        carrelloSession.setCarrelloCod(carrelloDB.getCarrelloCod());
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Prodotto prodotto : carrello) {
            s.append("\n").append(prodotto.toString());
        }
        return s.toString();
    }

    public void addProduct(Prodotto prodotto) throws SQLException {
        boolean flag = false;
        int index=-1;
        for(int i = 0; i < carrello.size(); i++){
            if(prodotto.getID()==carrello.get(i).getID()){
                flag = true;
                index=i;
            }
        }
        CarrelloDAO service = new CarrelloDAO();
        if(!flag){
            //il prodotto non era presente all'interno del carrello quindi posso aggiungerlo direttamente
            carrello.add(prodotto);
            //service.addCartDB(prodotto.getID(), CarrelloCod ,prodotto.getQuantità());
            service.delCarrelloFromComporre(this);
        }
        else{
            //il prodotto era già presente all'interno del carrello quindi devo solo aggiornare la sua quantità andando a sommare a quella già presente nel carrello, quella che è stata richiesta in info-pezzo
            carrello.get(index).setQuantita(carrello.get(index).getQuantita()+prodotto.getQuantita());
            //service.updateCarrelloDB(carrello.get(index).getID(), CarrelloCod, carrello.get(index).getQuantità());
            service.delCarrelloFromComporre(this);
        }
    }

    public void addSessionProduct(Prodotto prodotto) {
            boolean flag = false;
            int index=-1;
            for(int i = 0; i < carrello.size(); i++){
                if(prodotto.getID()==carrello.get(i).getID()){
                    flag = true;
                    index=i;
                }
            }
            CarrelloDAO service = new CarrelloDAO();
            if(!flag){
                //il prodotto non era presente all'interno del carrello quindi posso aggiungerlo direttamente
                carrello.add(prodotto);
            }
            else {
                //il prodotto era già presente all'interno del carrello quindi devo solo aggiornare la sua quantità andando a sommare a quella già presente nel carrello, quella che è stata richiesta in info-pezzo
                carrello.get(index).setQuantita(carrello.get(index).getQuantita() + prodotto.getQuantita());
            }
    }

    public Boolean isEmpty(){
        if(carrello!=null)
            return carrello.isEmpty();
        return null;
    }

    public double calculateTotal(){
        int sum = 0;
        for(int i = 0; i < getCarrello().size(); i++){
            sum += getCarrello().get(i).getPrezzo();
        }
        return sum;
    }

    //Rimuove un prodotto del carrello dalla sessione e ritorna la quantità del prododotto rimossa
    //è necessario ritornare la quantità per riaggiungerlo al catalogo di sessione
    public int removeProductByIdFromSession(int id){
        int quantity = 0;
        for(int i = 0; i < carrello.size(); i++){
            if(carrello.get(i).getID() == id){
                quantity = carrello.get(i).getQuantita();
                carrello.remove(i);
            }
        }
        return quantity;
    }

    public static Carrello createNewCarrello(Cliente c) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        int idCarrello = service.createCarrello();
        service.createNewOrdine(c, idCarrello);
        Carrello newCarrello = new Carrello();
        newCarrello.setCarrelloCod(idCarrello);
        return newCarrello;
    }

    public void forwardOrder(int idCarrello, String mail, String via, String provincia, String citta, Integer cap) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        service.setOrdineEvaso(idCarrello, mail);
        service.setIndirizzoOrdine(via, provincia, citta, cap, mail, idCarrello);
    }

    public void forwardOrder(int idCarrello, String mail) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        service.setOrdineEvaso(idCarrello, mail);
    }

    public int doRetriveQuantitaProdottoById(int id){
        for(Prodotto p : carrello){
            if(p.getID() == id){
                return p.getQuantita();
            }
        }
        return 0;
    }

}
