package Controller;

import Model.Archiviazione_.ArchivioDati;
import Model.Archiviazione_.ArchivioDatiDAO;
import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CASE_.CaseDAO;
import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.CPU_.Cpu;
import Model.CPU_.CpuDAO;
import Model.DISSIPATORE_.Dissipatore;
import Model.DISSIPATORE_.DissipatoreDAO;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.ImageManager;
import Model.MOBO_.Mobo;
import Model.MOBO_.MoboDAO;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.ProdottoDAO;
import Model.RAM_.Ram;
import Model.RAM_.RamDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Upload di un prodotto (info. e foto) nel DB/server

//Configurazione di multipart per permettere di prendere le immagini
@MultipartConfig(location = "/tmp/"
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "Upload", value = "/Upload")
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Marca = request.getParameter("marca");
        String Modello = request.getParameter("modello");
        String prezzo1 = request.getParameter("prezzo");
        //Il prezzo deve essere inserito alla maniera americana. In caso di errore lo correggo
        prezzo1=prezzo1.replace(",", ".");
        Double Prezzo = Double.parseDouble(prezzo1);
        Integer Quantita = Integer.parseInt(request.getParameter("quantita"));
        String Tipo = request.getParameter("tipo");
        //ImageManager necessario per salvare l'immagine
        ImageManager imgManager = new ImageManager();

        if(Marca==null || Modello==null || Prezzo==null || prezzo1==null ||  Quantita==null || Tipo==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer Wattaggio = null;
        if (request.getParameter("wattaggio") != null) {
            Wattaggio = Integer.parseInt(request.getParameter("wattaggio"));
        }
        Float Frequenza = null;
        if (request.getParameter("frequenza") != null) {
            Frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer N_Core = null;
        if (request.getParameter("N_Core") != null) {
            N_Core = Integer.parseInt(request.getParameter("N_Core"));
        }
        Integer N_Ram = null;
        if (request.getParameter("N_Ram") != null) {
            N_Ram = Integer.parseInt(request.getParameter("N_Ram"));
        }
        Integer N_Usb = null;
        if (request.getParameter("N_Usb") != null) {
            N_Usb = Integer.parseInt(request.getParameter("N_Usb"));
        }
        Integer N_Pci = null;
        if (request.getParameter("N_Pci") != null) {
            N_Pci = Integer.parseInt(request.getParameter("N_Pci"));
        }
        Integer MBs = null;
        if (request.getParameter("MBs") != null) {
            MBs = Integer.parseInt(request.getParameter("MBs"));
        }
        Integer Vram = null;
        if (request.getParameter("Vram") != null) {
            Vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Integer W_Cpu = null;
        if (request.getParameter("W_Cpu") != null) {
            W_Cpu = Integer.parseInt(request.getParameter("W_Cpu"));
        }
        Short FormaMobo = null;
        if (request.getParameter("formaMobo") != null) {
            FormaMobo = Short.parseShort(request.getParameter("formaMobo"));
        }

        //Restituisce la posizione relativa dell'immagine salvata partendo dalla radice del server
        //Prende in input 1:la path da "/" fino alla radice del server
        //                2:Il file da uploadare sottoforma di file Part
        //                3:Il nome con cui salvare il file
        String url = imgManager.saveImage(String.valueOf(request.getServletContext().getResource("/")), request.getPart("image"), Marca + Modello);
        String Descrizione = request.getParameter("descrizione");
        try {
            //Carica il prodotto nal DB.
            switch (Tipo) {
                case "CPU":
                    Cpu cpu = new Cpu();
                    cpu.setMarca(Marca);
                    cpu.setModello(Modello);
                    cpu.setPrezzo(Prezzo);
                    cpu.setQuantita(Quantita);
                    cpu.setWattaggio(Wattaggio);
                    cpu.setFrequenza(Frequenza);
                    cpu.setN_Core(N_Core);
                    cpu.setUrl(url);
                    cpu.setDescrizione(Descrizione);
                    CpuDAO.Upload(cpu);
                    break;
                case "CASE":
                    Case case_ = new Case();
                    case_.setMarca(Marca);
                    case_.setModello(Modello);
                    case_.setPrezzo(Prezzo);
                    case_.setQuantita(Quantita);
                    case_.setFormaMobo(FormaMobo);
                    case_.setUrl(url);
                    case_.setDescrizione(Descrizione);
                    CaseDAO.Upload(case_);
                    break;
                case "DISSIPATORE":
                    Dissipatore diss = new Dissipatore();
                    diss.setMarca(Marca);
                    diss.setModello(Modello);
                    diss.setPrezzo(Prezzo);
                    diss.setQuantita(Quantita);
                    diss.setW_Cpu(W_Cpu);
                    diss.setUrl(url);
                    diss.setDescrizione(Descrizione);
                    DissipatoreDAO.Upload(diss);
                    break;
                case "PSU":
                    Psu psu = new Psu();
                    psu.setMarca(Marca);
                    psu.setModello(Modello);
                    psu.setPrezzo(Prezzo);
                    psu.setQuantita(Quantita);
                    psu.setN_Watt(Wattaggio);
                    psu.setUrl(url);
                    PsuDAO.Upload(psu);
                    break;
                case "MOBO":
                    Mobo mobo = new Mobo();
                    mobo.setMarca(Marca);
                    mobo.setModello(Modello);
                    mobo.setPrezzo(Prezzo);
                    mobo.setQuantita(Quantita);
                    mobo.setForma(FormaMobo);
                    mobo.setN_RAM(N_Ram);
                    mobo.setN_USB(N_Usb);
                    mobo.setN_PCI(N_Pci);
                    mobo.setUrl(url);
                    mobo.setDescrizione(Descrizione);
                    MoboDAO.Upload(mobo);
                    break;
                case "RAM":
                    Ram ram = new Ram();
                    ram.setMarca(Marca);
                    ram.setModello(Modello);
                    ram.setPrezzo(Prezzo);
                    ram.setQuantita(Quantita);
                    ram.setFrequenza(Frequenza);
                    ram.setUrl(url);
                    ram.setDescrizione(Descrizione);
                    RamDAO.Upload(ram);
                    break;
                case "HDD":
                    Hdd hdd = new Hdd();
                    hdd.setMarca(Marca);
                    hdd.setModello(Modello);
                    hdd.setPrezzo(Prezzo);
                    hdd.setQuantita(Quantita);
                    hdd.setMBs(MBs);
                    hdd.setUrl(url);
                    hdd.setDescrizione(Descrizione);
                    ArchivioDatiDAO.Upload(hdd);
                    break;
                case "SSD":
                    Ssd ssd = new Ssd();
                    ssd.setMarca(Marca);
                    ssd.setModello(Modello);
                    ssd.setPrezzo(Prezzo);
                    ssd.setQuantita(Quantita);
                    ssd.setMBs(MBs);
                    ssd.setUrl(url);
                    ssd.setDescrizione(Descrizione);
                    ArchivioDatiDAO.Upload(ssd);
                    break;
                case "GPU":
                    Gpu gpu = new Gpu();
                    gpu.setMarca(Marca);
                    gpu.setModello(Modello);
                    gpu.setPrezzo(Prezzo);
                    gpu.setQuantita(Quantita);
                    gpu.setWattaggio(Wattaggio);
                    gpu.setFrequenza(Frequenza);
                    gpu.setVRam(Vram);
                    gpu.setUrl(url);
                    gpu.setDescrizione(Descrizione);
                    GpuDAO.Upload(gpu);
                    break;
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Rigenera il catalogo con i nuovi prodotti
        CatalogoDAO service = new CatalogoDAO();
        Catalogo newCatalogo = new Catalogo();
        try {
            newCatalogo.setCatalogo(service.doRetriveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession ss = request.getSession();
        ss.setAttribute("catalogo", newCatalogo);

        request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
