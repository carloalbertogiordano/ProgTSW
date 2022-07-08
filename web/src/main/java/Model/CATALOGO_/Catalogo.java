package Model.CATALOGO_;

import Model.Archiviazione_.HDD_.HddDAO;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.CASE_.CaseDAO;
import Model.CPU_.Cpu;
import Model.CPU_.CpuDAO;
import Model.Carrello_.Carrello;
import Model.DISSIPATORE_.Dissipatore;
import Model.DISSIPATORE_.DissipatoreDAO;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.MOBO_.MoboDAO;
import Model.PSU_.PsuDAO;
import Model.Prodotto;
import Model.RAM_.RamDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Prodotto> catalogo;

    public Catalogo(){
        catalogo = new ArrayList<Prodotto>();
    }

    public List<Prodotto> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Prodotto> catalgo) {
        this.catalogo = catalgo;
    }

    public void aggiornaQuantita(Carrello carrello){
        //Le quantità relative ai pezzi nel carrello sono le quantità richieste
        //Le quantità relative ai pezzi del catalo sono le quantità disponibili
        for(int i = 0; i < catalogo.size(); i++){
            for(int j = 0; j < carrello.getCarrello().size(); j++){
               if(getCatalogo().get(i).getID() == carrello.getCarrello().get(j).getID()){
                    System.out.println("Quantità disponibile: " + catalogo.get(i).getQuantità());
                   System.out.println("Quantità Richiesta: " + carrello.getCarrello().get(j).getQuantità());
                    catalogo.get(i).setQuantità(catalogo.get(i).getQuantità() - carrello.getCarrello().get(j).getQuantità());
                   System.out.println("Quantità rimanente: " +  catalogo.get(i).getQuantità());
                }
            }
        }
    }
    public void aggiornaQuantita(Prodotto p,int quantita){
        //Le quantità relative ai pezzi nel carrello sono le quantità richieste
        //Le quantità relative ai pezzi del catalo sono le quantità disponibili
        for(int i = 0; i < catalogo.size(); i++){
                if(catalogo.get(i).getID()==p.getID()){
                    System.out.println("Quantità disponibile: " + catalogo.get(i).getQuantità());
                    //System.out.println("Quantità Richiesta: " + carrello.getCarrello().get(j).getQuantità());
                    catalogo.get(i).setQuantità((catalogo.get(i).getQuantità() + p.getQuantità())-quantita);
                    //p.setQuantità(quantita);
                    System.out.println("Quantità rimanente: " +  catalogo.get(i).getQuantità());
                }
        }
    }

    public void aggiornaQuantita(Prodotto p){
        //Le quantità relative ai pezzi nel carrello sono le quantità richieste
        //Le quantità relative ai pezzi del catalo sono le quantità disponibili
        for (Prodotto prodotto : catalogo) {
            if (prodotto.getID() == p.getID()) {
                System.out.println("Quantità disponibile: " + prodotto.getQuantità());
                //System.out.println("Quantità Richiesta: " + carrello.getCarrello().get(j).getQuantità());
                prodotto.setQuantità((prodotto.getQuantità() - p.getQuantità()));
                //p.setQuantità(quantita);
                System.out.println("Quantità rimanente: " + prodotto.getQuantità());
            }
        }
    }

    public List<?> doRetriveByType(String type){
        List<Prodotto> list = new ArrayList<>();
        switch (type) {
            case "CPU":
                for(Prodotto p : catalogo) {
                    if (p.getTipo().equals("CPU"))
                        list.add(p);
                }
                break;
            case "MOBO":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("MOBO"))
                        list.add(p);
                break;
            case "CASE":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("CASE"))
                        list.add(p);
                break;
            case "DISSIPATORE":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("DISSIPATORE"))
                        list.add(p);
                break;
            case "GPU":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("GPU"))
                        list.add(p);
                break;
            case "PSU":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("PSU"))
                        list.add(p);
                break;
            case "RAM":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("RAM"))
                        list.add(p);
                break;
            case "HDD":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("HDD"))
                        list.add(p);
                break;
            case "SSD":
                for(Prodotto p : catalogo)
                    if(p.getTipo().equals("SSD"))
                        list.add(p);
                break;
            default:
                Prodotto prodotto = null;
                break;
        }
        return list;
    }

    public Prodotto doRetriveById(int id){
        for(int i = 0; i < catalogo.size(); i++){
            if(id == catalogo.get(i).getID()){
                return catalogo.get(i);
            }
        }
        return null;
    }

    public void addProdotto(Prodotto p){
        catalogo.add(p);
    }

    public Catalogo filterByMarca(String marca){
        Catalogo newCatalogo = new Catalogo();
        for(Prodotto p : catalogo){
            if((p.getMarca().toLowerCase()).contains(marca.toLowerCase())) {
                System.out.println("Vero per str: " + marca);
                newCatalogo.addProdotto(p);
            }
        }
        return newCatalogo;
    }
    public Catalogo filterByModello(String modello) {
        System.out.println("OKOOOK");
        Catalogo newCatalogo = new Catalogo();
        for(Prodotto p : catalogo){
            if((p.getModello().toLowerCase()).contains(modello.toLowerCase()))
                newCatalogo.addProdotto(p);
        }
        return newCatalogo;
    }

    public Catalogo filterByPrezzo(int val) {
        Catalogo c = new Catalogo();
        for(Prodotto p : catalogo){
            if(p.getPrezzo() < val)
                c.addProdotto(p);
        }
        return c;
    }

    public int getMaxPrice() {
        int max = -1;
        for(Prodotto p : catalogo)
            if(p.getPrezzo() > max)
                max = (int) p.getPrezzo();
        return max + 1;
    }
    public int getMinPrice(){
        int min = Integer.MAX_VALUE;
        for(Prodotto p : catalogo){
            if(p.getPrezzo() < min)
                min = (int) p.getPrezzo();
        }
        return min;
    }

    public boolean isEmpty(){
        return catalogo.size() == 0;
        //Fa quanto scritto sotto ma scritto semplificato
        /*if(catalogo.size() == 0)
            return true;
        return false;*/
    }
}
