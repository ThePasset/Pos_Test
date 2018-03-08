/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Einkaufsliste;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author passe
 */
public class Einkaufslistengenerator {
    //Add a commit to test the build function
    public void writeList(Einkaufsliste liste, File file) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Einkaufsliste.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(liste, file);
    }
}
