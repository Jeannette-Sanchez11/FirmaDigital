/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

//esta clase verifica la integridad del contenido del archivo además de la integridad de la clave publica

public class Verificador {

    //Metodo encargado de validar la firma electronica

    public boolean validarFirma(String rutaArchivo, String rutaFirma, String rutaClavePublica) throws Exception {
        byte[] datos = leerArchivo(rutaArchivo);
        byte[] firma = leerArchivo(rutaFirma);
        PublicKey clavePublica = leerClavePublicaDesdeArchivo(rutaClavePublica);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(clavePublica);
        signature.update(datos);

        return signature.verify(firma);

    }

 
    // Metodo encargado de leer el contenido de un archivo .txt y guardra su contendo en bytes

    public byte[] leerArchivo(String ruta) throws Exception {
        return Files.readAllBytes(Paths.get(ruta));
    }

  
    //Metodo capaz de obtener la clave publica que esta almacenada en un archivo

    public PublicKey leerClavePublicaDesdeArchivo(String ruta) throws Exception {
        byte[] keyBytes = leerArchivo(ruta);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
