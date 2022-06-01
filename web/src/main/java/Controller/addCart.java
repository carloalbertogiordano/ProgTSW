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
        int id = Integer.parseInt(request.getParameter("Id"));
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        try {
            if(ProdottoDAO.doRetriveById(id) instanceof Cpu){
                Cpu cpu = (Cpu) catalogo.doRetriveById(id);
                Cpu cpu_carrello= (Cpu) carrello.getById(id);
                System.out.println("CPUZ");
                if(cpu_carrello!=null) {
                    System.out.println("CPU c'era");
                    catalogo.aggiornaQuantita(cpu_carrello, cpu_carrello.getQuantità()+1);
                    carrello.addProduct(cpu);
                }
                else{
                    System.out.println("CPU no c'era");
                    cpu_carrello=new Cpu(cpu.getID(),cpu.getMarca(),cpu.getModello(),cpu.getPrezzo(),1,cpu.getWattaggio(),cpu.getFrequenza(),cpu.getN_Core(),cpu.getUrl(),cpu.getDescrizione());
                    carrello.addProduct(cpu_carrello);
                    catalogo.aggiornaQuantita(cpu_carrello);
                }
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Case){
                Case case_ = (Case) catalogo.doRetriveById(id);
                Case case_carrello= (Case) carrello.getById(id);
                System.out.println("CPUZ");
                if(case_carrello!=null) {
                    System.out.println("CASE c'era");
                    catalogo.aggiornaQuantita(case_carrello, case_carrello.getQuantità()+1);
                    carrello.addProduct(case_);
                }
                else{
                    System.out.println("CASE no c'era");
                    case_.setQuantità(1);
                    carrello.addProduct(case_);
                    case_carrello= (Case) carrello.getById(id);
                    catalogo.aggiornaQuantita(case_carrello, case_carrello.getQuantità()+1);
                }
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Gpu){
                Gpu gpu = (Gpu) catalogo.doRetriveById(id);
                Gpu gpu_carrello= (Gpu) carrello.getById(id);
                System.out.println("CPUZ");
                if(gpu_carrello!=null) {
                    System.out.println("GPU c'era");
                    catalogo.aggiornaQuantita(gpu_carrello, gpu_carrello.getQuantità()+1);
                    carrello.addProduct(gpu);
                }
                else{
                    System.out.println("CPU no c'era");
                    gpu.setQuantità(1);
                    carrello.addProduct(gpu);
                    gpu_carrello= (Gpu) carrello.getById(id);
                    catalogo.aggiornaQuantita(gpu_carrello, gpu_carrello.getQuantità()+1);
                }
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Dissipatore){
                Dissipatore dissipatore = (Dissipatore) catalogo.doRetriveById(id);
                Dissipatore dissipatore_carrello= (Dissipatore) carrello.getById(id);
                System.out.println("CPUZ");
                if(dissipatore_carrello!=null) {
                    System.out.println("CPU c'era");
                    catalogo.aggiornaQuantita(dissipatore_carrello, dissipatore_carrello.getQuantità()+1);
                    carrello.addProduct(dissipatore);
                }
                else{
                    System.out.println("CPU no c'era");
                    dissipatore.setQuantità(1);
                    carrello.addProduct(dissipatore);
                    dissipatore_carrello= (Dissipatore) carrello.getById(id);
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


