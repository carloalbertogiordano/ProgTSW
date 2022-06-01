package Controller;

import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.Carrello_.Carrello;
import Model.Catalogo;
import Model.CatalogoDAO;
import Model.Cliente_.Cliente;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addCart", value = "/addCart")
public class addCart extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //take product id
        int id = Integer.parseInt(request.getParameter("Id"));
        //take quantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //take the session and session attribute carrello and catalogo
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        try {
            if(ProdottoDAO.doRetriveById(id) instanceof Cpu){
                //take the product from catalogo by his id
                Cpu cpu = (Cpu) catalogo.doRetriveById(id);
                //take the product from carrello by his id (we need it to update quantity in catalogo)
                Cpu cpu_carrello= (Cpu) carrello.getById(id);
                if(cpu_carrello!=null) {
                    /*
                    *if product from carrello is null it's because the product we selected in catalogo there isn't in carrello before, so we can add it in carrello and update quantity in catalogo with only
                    *the quantity we select
                    * */
                    catalogo.aggiornaQuantita(cpu_carrello, cpu_carrello.getQuantità()+quantity);
                    carrello.addProduct(cpu);
                }
                else{
                    /*
                    * If product from carrello isn't null it's because we selected a product from catalogo that alrady exists in carrello. So we need to add the quantity of product from carrello to quantity
                    * of product we selected and update correctly the quantity in catalogo
                    * */
                    cpu_carrello=new Cpu(cpu.getID(),cpu.getMarca(),cpu.getModello(),cpu.getPrezzo(),quantity,cpu.getWattaggio(),cpu.getFrequenza(),cpu.getN_Core(),cpu.getUrl(),cpu.getDescrizione());
                    carrello.addProduct(cpu_carrello);
                    catalogo.aggiornaQuantita(cpu_carrello);
                }
            }
            //We can repeat the process for all type of product
            else if(ProdottoDAO.doRetriveById(id) instanceof Case){
                Case case_ = (Case) catalogo.doRetriveById(id);
                Case case_carrello= (Case) carrello.getById(id);
                if(case_carrello!=null) {
                    catalogo.aggiornaQuantita(case_carrello, case_carrello.getQuantità()+quantity);
                    carrello.addProduct(case_);
                }
                else{
                    case_carrello = new Case(case_.getID(), case_.getMarca(), case_.getModello(), case_.getPrezzo(), quantity, case_.getFormaMobo(), case_.getUrl(), case_.getDescrizione());
                    carrello.addProduct(case_carrello);
                    catalogo.aggiornaQuantita(case_carrello);
                }
            }
            else if(catalogo.doRetriveById(id) instanceof Gpu){
                Gpu gpu = (Gpu) catalogo.doRetriveById(id);
                Gpu gpu_carrello= (Gpu) carrello.getById(id);
                if(gpu_carrello!=null) {
                    catalogo.aggiornaQuantita(gpu_carrello, gpu_carrello.getQuantità()+quantity);
                    carrello.addProduct(gpu);
                }
                else{
                    gpu_carrello= new Gpu(gpu.getID(), gpu.getMarca(), gpu.getModello(), gpu.getPrezzo(), quantity, gpu.getWattaggio(), gpu.getFrequenza(), gpu.getVRam(), gpu.getUrl(), gpu.getDescrizione());
                    carrello.addProduct(gpu_carrello);
                    catalogo.aggiornaQuantita(gpu_carrello);
                }
            }
            else if(catalogo.doRetriveById(id) instanceof Dissipatore){
                Dissipatore dissipatore = (Dissipatore) catalogo.doRetriveById(id);
                Dissipatore dissipatore_carrello= (Dissipatore) carrello.getById(id);
                if(dissipatore_carrello!=null) {
                    catalogo.aggiornaQuantita(dissipatore_carrello, dissipatore_carrello.getQuantità()+quantity);
                    carrello.addProduct(dissipatore);
                }
                else{
                    dissipatore_carrello= new Dissipatore(dissipatore.getID(), dissipatore.getMarca(), dissipatore.getModello(), dissipatore.getPrezzo(), quantity, dissipatore.getW_Cpu(), dissipatore.getUrl(), dissipatore.getDescrizione());
                    catalogo.aggiornaQuantita(dissipatore_carrello, dissipatore_carrello.getQuantità()+1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}


