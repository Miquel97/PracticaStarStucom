/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprobacionFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.historiales.Historiales;
import model.sudokus.Sudokus;
import model.usuarios.Usuarios;
import practicajaxb.PracticaJAXB;
import static practicajaxb.PracticaJAXB.historiales;
import static practicajaxb.PracticaJAXB.sudokus;
import static practicajaxb.PracticaJAXB.usuarios;

/**
 * Comprobacion de si existe el fichero historiales.xml
 * @author Miquel
 */

public class CompFicheros {
    public static void comprobarFileHistoriales() {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Historiales.class);
            File archivo = new File("historiales.xml");
            if (!archivo.exists()) {
                Marshaller mh = contexto.createMarshaller();
                mh.marshal(historiales, new File("historiales.xml"));

            } else {
                Unmarshaller unm = contexto.createUnmarshaller();
                historiales = (Historiales) unm.unmarshal(new File("historiales.xml"));

            }

        } catch (JAXBException ex) {
            Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Comprobacion de si existe el fichero usuarios.xml
 */
    public static void comprobarFileUsuarios() {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Usuarios.class);
            File archivo = new File("usuarios.xml");
            if (!archivo.exists()) {

                Marshaller mh = contexto.createMarshaller();
                mh.marshal(usuarios, new File("usuarios.xml"));

            } else {
                Unmarshaller unm = contexto.createUnmarshaller();
                usuarios = (Usuarios) unm.unmarshal(new File("usuarios.xml"));

            }

        } catch (JAXBException ex) {
            Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Comprobacion de si existe el fichero sudokus.xml
     */
    public static void comprobacionFichero() {
        File archivo = new File("sudokus.xml");

        if (!archivo.exists()) {
            FileReader fr = null;
            try {
                JAXBContext contexto = JAXBContext.newInstance(Sudokus.class);
                File sdks = new File("sudokus.txt");
                fr = new FileReader(sdks);
                BufferedReader br = new BufferedReader(fr);
                String linea = br.readLine();

                while (linea != null) {
                    String[] cortar = linea.split(" ");
                    Sudokus.Sudoku s = new Sudokus.Sudoku();
                    s.setLevel(new BigInteger(cortar[1]));
                    s.setDescription(cortar[2]);
                    String linea2 = br.readLine();
                    s.setProblem(linea2);
                    String linea3 = br.readLine();
                    s.setSolved(linea3);
                    sudokus.getSudoku().add(s);

                    linea = br.readLine();
                }

                Marshaller mh = contexto.createMarshaller();
                mh.marshal(sudokus, new File("sudokus.xml"));

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                JAXBContext contexto = JAXBContext.newInstance(Sudokus.class);
                Unmarshaller u = contexto.createUnmarshaller();
                sudokus = (Sudokus) u.unmarshal(new File("sudokus.xml"));

            } catch (JAXBException ex) {
                Logger.getLogger(PracticaJAXB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }


}
