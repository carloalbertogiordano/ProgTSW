package Model.Carrello_;

import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.SQLException;
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

    public Carrello doCheckList(Carrello carrello) throws SQLException {
        for(int i = 0; i < carrello.getCarrello().size(); i++){
            if(!ProdottoDAO.doCheckDisponibilita(carrello.getCarrello().get(i))){
                if(carrello.getCarrello().get(i).getQuantità() == 0){
                    carrello.getCarrello().remove(i);
                }
                else{
                    int quantitaDisponible = carrello.getCarrello().get(i).getQuantità();
                    CarrelloDAO service = new CarrelloDAO();
                    service.doUpdateQuantitaRichiestaById(carrello.getCarrello().get(i).getID(), quantitaDisponible);
                }
            }
        }
        return carrello;
    }

    public void aggiornaQuantita(int id){

    }
}
