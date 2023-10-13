/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Noe Guillen Gerardo
 */
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * Clase que crea la firma de los documentos 
 */
public class Firmador {

    public void firmarArchivo(String ruta, PrivateKey clavePrivada, String rutaFirma) throws Exception {
        //crear firma
        byte[] data = leerArchivo(ruta);
        Signature dsa = Signature.getInstance("SHA1withRSA");
        dsa.initSign(clavePrivada);
        dsa.update(data);
        byte[] firma = dsa.sign();

        //llamada al metodo que guarda la firma 
        guardarFirma(rutaFirma, firma);

    }

    // Metodo encargado de leer el contenido de un archivo .txt y guardra su contendo en bytes
    public byte[] leerArchivo(String ruta) throws Exception {
        return Files.readAllBytes(Paths.get(ruta));
    }

    //este metodo sirve para guardar la firma generada en un archivo en la ruta deseada por el usuario
    public void guardarFirma(String ruta, byte[] firma) throws Exception {
        FileOutputStream fos = new FileOutputStream(ruta);
        fos.write(firma);
        fos.close();
    }

}
