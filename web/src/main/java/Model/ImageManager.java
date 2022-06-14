package Model;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import javax.naming.Context;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class ImageManager {

    public ImageManager() {}

    //Concatena alla path da / fino alla root di tomcat "Images/" che è la cartella dove vogliamo salvare l'immagine
    //e rimuove la scritta "file:" che si trova all'inizio per risolvere il problema che
    //viene creato dal metodo getResource("/") del contesto, che restituisce la path
    //ma preceduta da "file:"
    private String getSavePath(String s){
        String path = (s + "Images/");
        path = path.replace("file:", "");
        return path;
    }
    //Scrive l'immagine rappresentata come Part nella imagePath con nome name
    private void writeImage(Part filePart, String imagePath, String name) throws IOException {
        System.out.println("Writing to "+imagePath+name);
        filePart.write(imagePath+name);
    }
    //Restituisce la path relativa dalla root di tomcat
    //ES: Images/nomeFile.jpg
    public String saveImage(String rootPath, Part filePart, String saveName) throws IOException {
        //Prendi la path completa fino a Images
        String imagePath = getSavePath(rootPath);
        writeImage(filePart, imagePath, saveName);
        return "Images/"+saveName;
    }
}
