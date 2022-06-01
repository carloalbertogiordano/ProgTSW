package Model;

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
import Model.RAM_.RamDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Prodotto> catalogo;

    public Catalogo(){

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
        for(int i = 0; i < catalogo.size(); i++){
            if(catalogo.get(i).getID()==p.getID()){
                System.out.println("Quantità disponibile: " + catalogo.get(i).getQuantità());
                //System.out.println("Quantità Richiesta: " + carrello.getCarrello().get(j).getQuantità());
                catalogo.get(i).setQuantità((catalogo.get(i).getQuantità() - p.getQuantità()));
                //p.setQuantità(quantita);
                System.out.println("Quantità rimanente: " +  catalogo.get(i).getQuantità());
            }
        }
    }
    public List<?> doRetriveByType(String type){
        switch (type){
            case "CPU":
                List<Cpu> listCpu = new ArrayList();
                for(Prodotto p: catalogo)
                    if(p instanceof Cpu)
                        listCpu.add((Cpu) p);
                return listCpu;
            case "CASE":
                List<Case> listCase = new ArrayList();
                for(Prodotto p: catalogo)
                    if(p instanceof Case)
                        listCase.add((Case) p);
                return listCase;
            case "GPU":
                List<Gpu> listGpu = new ArrayList();
                for(Prodotto p: catalogo)
                    if(p instanceof Gpu)
                        listGpu.add((Gpu) p);
                return listGpu;
            case "DISSIPATORE":
                List<Dissipatore> listDissipatore = new ArrayList();
                for(Prodotto p: catalogo)
                    if(p instanceof Dissipatore)
                        listDissipatore.add((Dissipatore) p);
                return listDissipatore;
            default:
                return null;
        }
    }

    public Prodotto doRetriveById(int id){
        for(int i = 0; i < catalogo.size(); i++){
            if(id == catalogo.get(i).getID()){
                return catalogo.get(i);
            }
        }
        return null;
    }
}
