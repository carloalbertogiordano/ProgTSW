package Controller;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.Carrello_.Carrello;
import Model.CATALOGO_.Catalogo;
import Model.Cliente_.Cliente;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.RAM_.Ram;
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
public class addCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //take product id
        int id = Integer.parseInt(request.getParameter("Id"));
        //take quantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //take the session and session attribute carrello, catalogo and Cliente
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        //We take product from the catalogo by its id
        Prodotto p = catalogo.doRetriveById(id);

        switch (p.getTipo()) {
            case "CPU" :
                //We cast the product to CPU
                Cpu cpu = (Cpu) catalogo.doRetriveById(id);
                //take the product from carrello by its id (we need it to update quantity in catalogo)
                Cpu cpu_carrello = new Cpu();
                cpu_carrello.setID(cpu.getID());
                cpu_carrello.setMarca(cpu.getMarca());
                cpu_carrello.setModello(cpu.getModello());
                cpu_carrello.setPrezzo(cpu.getPrezzo());
                cpu_carrello.setQuantita(quantity);
                cpu_carrello.setWattaggio(cpu.getWattaggio());
                cpu_carrello.setFrequenza(cpu.getFrequenza());
                cpu_carrello.setN_Core(cpu.getN_Core());
                cpu_carrello.setUrl(cpu.getUrl());
                cpu_carrello.setDescrizione(cpu.getDescrizione());
                addToCart(cliente, carrello, catalogo, cpu_carrello);
            break;
            //We can repeat the process for all type of product
            case "MOBO" :
                Mobo mobo = (Mobo) catalogo.doRetriveById(id);
                Mobo mobo_carrello = new Mobo();//(mobo.getID(), mobo.getMarca(), mobo.getModello(), mobo.getPrezzo(), quantity, mobo.getForma(), mobo.getN_RAM(), mobo.getN_PCI(), mobo.getN_USB(), mobo.getUrl(), mobo.getDescrizione());
                mobo_carrello.setID(mobo.getID());
                mobo_carrello.setMarca(mobo.getMarca());
                mobo_carrello.setModello(mobo.getModello());
                mobo_carrello.setPrezzo(mobo.getPrezzo());
                mobo_carrello.setQuantita(quantity);
                mobo_carrello.setForma(mobo.getForma());
                mobo_carrello.setN_USB(mobo.getN_USB());
                mobo_carrello.setN_PCI(mobo.getN_PCI());
                mobo_carrello.setN_RAM(mobo.getN_RAM());
                mobo_carrello.setUrl(mobo.getUrl());
                mobo_carrello.setDescrizione(mobo.getDescrizione());
                addToCart(cliente, carrello, catalogo, mobo_carrello);
                break;
            case "RAM" :
                Ram ram = (Ram) p;
                Ram ram_carrello = new Ram();
                ram_carrello.setID(ram.getID());
                ram_carrello.setMarca(ram.getMarca());
                ram_carrello.setModello(ram.getModello());
                ram_carrello.setPrezzo(ram.getPrezzo());
                ram_carrello.setQuantita(quantity);
                ram_carrello.setFrequenza(ram.getFrequenza());
                ram_carrello.setUrl(ram.getUrl());
                ram_carrello.setDescrizione(ram.getDescrizione());
                addToCart(cliente, carrello, catalogo, ram_carrello);
                break;
            case "HDD" :
                Hdd hdd = (Hdd) p;
                Hdd hdd_carrello = new Hdd();
                hdd_carrello.setID(hdd.getID());
                hdd_carrello.setMarca(hdd.getMarca());
                hdd_carrello.setModello(hdd.getModello());
                hdd_carrello.setPrezzo(hdd.getPrezzo());
                hdd_carrello.setQuantita(quantity);
                hdd_carrello.setMBs(hdd.getMBs());
                hdd_carrello.setUrl(hdd.getUrl());
                hdd_carrello.setDescrizione(hdd.getDescrizione());
                addToCart(cliente, carrello, catalogo, hdd_carrello);
                break;
            case "SSD" :
                Ssd ssd = (Ssd) p;
                Ssd ssd_carrello = new Ssd();
                ssd_carrello.setID(ssd.getID());
                ssd_carrello.setMarca(ssd.getMarca());
                ssd_carrello.setModello(ssd.getModello());
                ssd_carrello.setPrezzo(ssd.getPrezzo());
                ssd_carrello.setQuantita(quantity);
                ssd_carrello.setMBs(ssd.getMBs());
                ssd_carrello.setUrl(ssd.getUrl());
                ssd_carrello.setDescrizione(ssd.getDescrizione());
                addToCart(cliente, carrello, catalogo, ssd_carrello);
                break;
            case "PSU" :
                Psu psu = (Psu) p;
                Psu psu_carrello = new Psu();
                psu_carrello.setID(psu.getID());
                psu_carrello.setMarca(psu.getMarca());
                psu_carrello.setModello(psu.getModello());
                psu_carrello.setPrezzo(psu.getPrezzo());
                psu_carrello.setQuantita(quantity);
                psu_carrello.setN_Watt(psu.getN_Watt());
                psu_carrello.setUrl(psu.getUrl());
                psu_carrello.setDescrizione(psu.getDescrizione());
                addToCart(cliente, carrello, catalogo, psu_carrello);
                break;
            case "CASE" :
                Case case_ = (Case) catalogo.doRetriveById(id);
                Case case_carrello = new Case();
                case_carrello.setID(case_.getID());
                case_carrello.setMarca(case_.getMarca());
                case_carrello.setModello(case_.getModello());
                case_carrello.setPrezzo(case_.getPrezzo());
                case_carrello.setQuantita(quantity);
                case_carrello.setFormaMobo(case_.getFormaMobo());
                case_carrello.setUrl(case_.getUrl());
                case_carrello.setDescrizione(case_.getDescrizione());
                addToCart(cliente, carrello, catalogo, case_carrello);
                break;
            case "GPU" :
                Gpu gpu = (Gpu) catalogo.doRetriveById(id);
                Gpu gpu_carrello = new Gpu();
                gpu_carrello.setID(gpu.getID());
                gpu_carrello.setMarca(gpu.getMarca());
                gpu_carrello.setModello(gpu.getModello());
                gpu_carrello.setPrezzo(gpu.getPrezzo());
                gpu_carrello.setQuantita(quantity);
                gpu_carrello.setWattaggio(gpu.getWattaggio());
                gpu_carrello.setFrequenza(gpu.getFrequenza());
                gpu_carrello.setVRam(gpu.getVRam());
                gpu_carrello.setUrl(gpu.getUrl());
                gpu_carrello.setDescrizione(gpu.getDescrizione());
                addToCart(cliente, carrello, catalogo, gpu_carrello);
                break;
            case "DISSIPATORE" :
                Dissipatore dissipatore = (Dissipatore) catalogo.doRetriveById(id);
                Dissipatore dissipatore_carrello = new Dissipatore();//(dissipatore.getID(), dissipatore.getMarca(), dissipatore.getModello(), dissipatore.getPrezzo(), quantity, dissipatore.getW_Cpu(), dissipatore.getUrl(), dissipatore.getDescrizione());
                dissipatore_carrello.setID(dissipatore.getID());
                dissipatore_carrello.setMarca(dissipatore.getMarca());
                dissipatore_carrello.setModello(dissipatore.getModello());
                dissipatore_carrello.setPrezzo(dissipatore.getPrezzo());
                dissipatore_carrello.setQuantita(quantity);
                dissipatore_carrello.setW_Cpu(dissipatore.getW_Cpu());
                dissipatore_carrello.setUrl(dissipatore.getUrl());
                dissipatore_carrello.setDescrizione(dissipatore.getDescrizione());
                addToCart(cliente, carrello, catalogo, dissipatore_carrello);
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrello.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addToCart(Cliente cliente, Carrello carrello, Catalogo catalogo, Prodotto prodotto){
        if (cliente != null) {
            try {
                carrello.addProduct(prodotto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            //Even if user is not logged you have to update Carrello and Catalogo
            carrello.addSessionProduct(prodotto);
        }
        catalogo.aggiornaQuantita(prodotto);

    }
}


