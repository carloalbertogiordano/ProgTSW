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

    public static List<?> doRetriveByType(String type) throws SQLException {
        List<?> list = new ArrayList<>();
        switch (type) {
            case "CPU":
                CpuDAO cDAO = new CpuDAO();
                list = cDAO.doRetriveByType();
                break;
            case "MOBO":
                MoboDAO mDAO = new MoboDAO();
                list= mDAO.doRetriveByType();
                break;
            case "CASE":
                CaseDAO caDAO = new CaseDAO();
                list = caDAO.doRetriveByType();
                break;
            case "DISSIPATORE":
                DissipatoreDAO dDAO = new DissipatoreDAO();
                list = dDAO.doRetriveByType();
                break;
            case "GPU":
                GpuDAO gDAO = new GpuDAO();
                list = gDAO.doRetriveByType();
                break;
            case "PSU":
                PsuDAO pDAO = new PsuDAO();
                list = pDAO.doRetriveByType();
                break;
            case "RAM":
                RamDAO rDAO = new RamDAO();
                list = rDAO.doRetriveByType();
                break;
            case "HDD":
                HddDAO hDAO = new HddDAO();
                list = hDAO.doRetriveByType();
                break;
            case "SSD":
                SsdDAO sDAO = new SsdDAO();
                list = sDAO.doRetriveByType();
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
            if(p.getMarca().equals(marca))
                newCatalogo.addProdotto(p);
        }
        return newCatalogo;
    }

}
