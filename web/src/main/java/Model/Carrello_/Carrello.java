package Model.Carrello_;

import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<Prodotto> carrello;
    private int CarrelloCod;
    private double prezzo;
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
        this.CarrelloCod = codice;
        this.prezzo = prezzo;
    }

    public List<Prodotto> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Prodotto> carrello) {
        this.carrello = carrello;
    }

    public int getCarrelloCod() {
        return CarrelloCod;
    }

    public void setCarrelloCod(int carrelloCod) {
        CarrelloCod = carrelloCod;
    }

    public Prodotto getById(int id) {
        for(Prodotto p: carrello)
            if(p.getID()==id)
                return p;
        return null;
    }

    public Carrello doCheckList(Carrello carrello) throws SQLException {
        for(int i = 0; i < carrello.getCarrello().size(); i++){
            if(!ProdottoDAO.doCheckDisponibilita(carrello.getCarrello().get(i))){
                if(carrello.getCarrello().get(i).getQuantità() == 0){
                    carrello.getCarrello().remove(i);
                }
                else{
                    int quantitaDisponible = carrello.getCarrello().get(i).getQuantità();
                    CarrelloDAO service = new CarrelloDAO();
                    service.doUpdateQuantitaRichiestaById(carrello.getCarrello().get(i).getID(), carrello.getCarrelloCod(), quantitaDisponible);
                }
            }
        }
        return carrello;
    }

    //Quanrtità pezzo è quantità richiesta
    public  Carrello joinCarrelli(Carrello carrelloDB){
        Carrello carrelloSession = this;
        if(carrelloSession.getCarrello()!=null)
            for(int i = 0; i < carrelloSession.getCarrello().size(); i++){
                if(carrelloDB.getCarrello()!=null)
                    for(int j = 0; j < carrelloDB.getCarrello().size(); j++){
                        if(carrelloSession.getCarrello().get(i).getID() == carrelloDB.getCarrello().get(j).getID()){
                            int quantita = carrelloSession.getCarrello().get(i).getQuantità() +  carrelloDB.getCarrello().get(j).getQuantità();
                            carrelloSession.getCarrello().get(i).setQuantità(quantita);
                            carrelloDB.getCarrello().remove(j);
                        }
                    }
            }
        for(Prodotto p : carrelloDB.getCarrello()){
            carrelloSession.getCarrello().add(p);
        }
        return carrelloSession;
    }

    public String toString() {
        String s = "";
        for (Prodotto prodotto : carrello) {
            s = s + "\n" + prodotto.toString();
        }
        return s;
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
        if(flag==false){
            //il prodotto non era presente all'interno del carrello quindi posso aggiungerlo direttamente
            carrello.add(prodotto);
            service.addCartDB(prodotto.getID(), CarrelloCod ,prodotto.getQuantità());
        }
        else{
            //il prodotto era già presente all'interno del carrello quindi devo solo aggiornare la sua quantità andando a sommare a quella già presente nel carrello, quella che è stata richiesta in info-pezzo
            carrello.get(index).setQuantità(carrello.get(index).getQuantità()+prodotto.getQuantità());
            service.updateCarrelloDB(carrello.get(index).getID(), CarrelloCod, carrello.get(index).getQuantità());
        }
    }

    public boolean isEmpty(){
        return carrello.isEmpty();
    }
}
