package Controller;

import Model.Archiviazione_.ArchivioDati;
import Model.Archiviazione_.ArchivioDatiDAO;
import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.HDD_.HddDAO;
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
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;
import Model.RAM_.RamDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(location = "/tmp/"
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

////Aggiorna il prodotto nal DB e nella sessione
@WebServlet(name = "Aggiorna", value = "/Aggiorna")
public class Aggiorna extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        String desc = request.getParameter("desc");
        String url = request.getParameter("url");
        String tipo = request.getParameter("tipo");
        Part image = null;

        try {
            image = request.getPart("image");
        } catch (IOException | ServletException ignored) {
            //Non è necessario lancare eccezioni poichè in caso rimanga null
            //significa che non abbiamo modificato l'immagine'
        }

        //Nel caso ho caricato una nuova immagine eliino la cartella e la ricreo
        if(ImageManager.isImage(image)){
            String rootPath = String.valueOf(request.getServletContext().getResource("/"));
            //ImageManager necessario per salvare l'immagine
            ImageManager imgManager = new ImageManager();
            imgManager.deleteImageDir(url, rootPath);
            url = imgManager.saveImage(rootPath, image, marca+modello);
        }
        //Nel caso NON ho caricato una nuova immagine ma ho cambiato marca e/o modello
        //devo rinominare la cartella
        else{
            Prodotto p;
            try {
                p = ProdottoDAO.doRetriveById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(!p.getMarca().equals(marca) || !p.getModello().equals(modello)){
                String rootPath = String.valueOf(request.getServletContext().getResource("/"));
                System.out.println(p.getUrl());
                ImageManager imgManager = new ImageManager();
                imgManager.renameFolder(rootPath, p.getUrl(), p.getMarca(), p.getUrl(), marca, modello);
                url = "Images/"+marca+modello;
            }
        }

        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer wattaggio = null;
        if (request.getParameter("watt") != null) {
            wattaggio = Integer.parseInt(request.getParameter("watt"));
        }
        Float frequenza = null;
        if (request.getParameter("frequenza") != null) {
            frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer N_Core = null;
        if (request.getParameter("numCore") != null) {
            N_Core = Integer.parseInt(request.getParameter("numCore"));
        }
        Integer N_Ram = null;
        if (request.getParameter("nRam") != null) {
            N_Ram = Integer.parseInt(request.getParameter("nRam"));
        }
        Integer N_Usb = null;
        if (request.getParameter("nUsb") != null) {
            N_Usb = Integer.parseInt(request.getParameter("nUsb"));
        }
        Integer N_Pci = null;
        if (request.getParameter("nPci") != null) {
            N_Pci = Integer.parseInt(request.getParameter("nPci"));
        }
        Integer MBs = null;
        if (request.getParameter("mbs") != null) {
            MBs = Integer.parseInt(request.getParameter("mbs"));
        }
        Integer Vram = null;
        if (request.getParameter("Vram") != null) {
            Vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Integer W_Cpu = null;
        if (request.getParameter("wCpu") != null) {
            W_Cpu = Integer.parseInt(request.getParameter("wCpu"));
        }
        Short formaMobo = null;
        if (request.getParameter("forma") != null) {
            formaMobo = Short.parseShort(request.getParameter("forma"));
        }

        try {
            //Aggiorna il prodotto nal DB. Il metodo Upload gestisce eventuali paramentri nulli
            switch (tipo) {
                case "CPU":
                    //CpuDAO.Update(new Cpu(id, marca, modello, prezzo, quantita, wattaggio, frequenza, N_Core, url, desc));
                    Cpu cpu = new Cpu();
                    cpu.setID(id);
                    cpu.setMarca(marca);
                    cpu.setModello(modello);
                    cpu.setPrezzo(prezzo);
                    cpu.setQuantita(quantita);
                    cpu.setWattaggio(wattaggio);
                    cpu.setFrequenza(frequenza);
                    cpu.setN_Core(N_Core);
                    cpu.setUrl(url);
                    cpu.setDescrizione(desc);
                    CpuDAO.Update(cpu);
                    break;
                case "CASE":
                    Case case_ = new Case();
                    case_.setID(id);
                    case_.setMarca(marca);
                    case_.setModello(modello);
                    case_.setPrezzo(prezzo);
                    case_.setQuantita(quantita);
                    case_.setFormaMobo(formaMobo);
                    case_.setUrl(url);
                    case_.setDescrizione(desc);
                    CaseDAO.Update(case_);
                    break;
                case "DISSIPATORE":
                    Dissipatore diss = new Dissipatore();
                    diss.setID(id);
                    diss.setMarca(marca);
                    diss.setModello(modello);
                    diss.setPrezzo(prezzo);
                    diss.setQuantita(quantita);
                    diss.setW_Cpu(W_Cpu);
                    diss.setUrl(url);
                    diss.setDescrizione(desc);
                    DissipatoreDAO.Update(diss);
                    break;
                case "PSU":
                    PsuDAO.Update(new Psu(id, marca, modello, prezzo, quantita, wattaggio, url, desc));
                    break;
                case "MOBO":
                    MoboDAO.Update(new Mobo(id, marca, modello, prezzo, quantita, formaMobo, N_Ram, N_Usb, N_Pci, url, desc));
                    break;
                case "RAM":
                    RamDAO.Update(new Ram(id, marca, modello, prezzo, quantita, frequenza, url, desc));
                    break;
                case "HDD":
                    Hdd hdd = new Hdd();
                    hdd.setID(id);
                    hdd.setMarca(marca);
                    hdd.setModello(modello);
                    hdd.setPrezzo(prezzo);
                    hdd.setQuantita(quantita);
                    hdd.setMBs(MBs);
                    hdd.setUrl(url);
                    hdd.setDescrizione(desc);
                    ArchivioDatiDAO.Update(hdd);
                    break;
                case "SSD":
                    Ssd ssd = new Ssd();
                    ssd.setID(id);
                    ssd.setMarca(marca);
                    ssd.setModello(modello);
                    ssd.setPrezzo(prezzo);
                    ssd.setQuantita(quantita);
                    ssd.setMBs(MBs);
                    ssd.setUrl(url);
                    ssd.setDescrizione(desc);
                    ArchivioDatiDAO.Update(ssd);
                    break;
                case "GPU":
                    Gpu gpu = new Gpu();
                    gpu.setID(id);
                    gpu.setMarca(marca);
                    gpu.setModello(modello);
                    gpu.setPrezzo(prezzo);
                    gpu.setQuantita(quantita);
                    gpu.setWattaggio(wattaggio);
                    gpu.setFrequenza(frequenza);
                    gpu.setVRam(Vram);
                    gpu.setUrl(url);
                    gpu.setDescrizione(desc);
                    GpuDAO.Update(gpu);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Rigenera il catalogo con i nuovi prodotti
        CatalogoDAO service = new CatalogoDAO();
        Catalogo newCatalogo;
        try {
            newCatalogo = service.doRetriveAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession ss = request.getSession();
        ss.setAttribute("catalogo", newCatalogo);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
