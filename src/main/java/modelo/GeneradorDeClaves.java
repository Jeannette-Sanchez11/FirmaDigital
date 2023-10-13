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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

//Clase capaz de generar un par de claves RSA y de asegurar adecuadamente la clave privada (mediante un password)

public class GeneradorDeClaves {

    //HashMap que almacena el par de claves generado con un password (o key) de identificacion.
    private HashMap<String, KeyPair> claves;

    //generador de claves
    private KeyPairGenerator generador;

    public GeneradorDeClaves() throws NoSuchAlgorithmException {
        generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(1024);
        claves = new HashMap<>(100);
    }

    //Genera una par de claves (clave privada y clave publica)

    public void generarClave(String password) {
        claves.put(password, generador.generateKeyPair());
    }

    //Exporta una clave publica

    public void exportarClavePublica(String rutaClavePublica, String passwordKeyPair) throws Exception {
        KeyPair parDeClaves = claves.get(passwordKeyPair);
        if (parDeClaves == null) {
            throw new Exception("No existe una clave publica guardada con el password especificado");
        } else {
            FileOutputStream fos = new FileOutputStream(rutaClavePublica);
            fos.write(parDeClaves.getPublic().getEncoded());
            fos.close();
        }

    }

    public HashMap<String, KeyPair> getClaves() {
        return claves;
    }

    public void setClaves(HashMap<String, KeyPair> claves) {
        this.claves = claves;
    }

    public KeyPairGenerator getGenerador() {
        return generador;
    }

    public void setGenerador(KeyPairGenerator generador) {
        this.generador = generador;
    }

}
