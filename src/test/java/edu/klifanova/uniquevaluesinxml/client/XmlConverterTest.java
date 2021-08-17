package edu.klifanova.uniquevaluesinxml.client;


import edu.klifanova.uniquevaluesinxml.clent.XmlConverter;
import edu.klifanova.uniquevaluesinxml.model.Catalog;
import edu.klifanova.uniquevaluesinxml.model.Mark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class XmlConverterTest {

    private XmlConverter xmlConverter = new XmlConverter();

    @Test
    public void converter_ValidDataFromXmlFile_ModificationName() throws IOException {
        String xml = new String(getClass().getClassLoader().getResourceAsStream("cars.xml").readAllBytes());

        var catalog = xmlConverter.converterXml(xml, Catalog.class);

        assertNotNull(catalog);
        var markList = catalog.getMarks();
        assertNotNull(markList);
        assertEquals(3, markList.size());
        var mark = markList.get(0);
        assertNotNull(mark);
        var folderList = mark.getFolders();
        assertNotNull(folderList);
        assertEquals(6, folderList.size());
        var folder = folderList.get(0);
        assertNotNull(folder);
        var modificationList = folder.getModifications();
        assertNotNull(modificationList);
        assertEquals(4, modificationList.size());
        var modification = modificationList.get(0);
        assertNotNull(modification);
        var modificationName = modification.getName();
        assertNotNull(modificationName);
        assertEquals(modificationName, "CCX Edition 4.8 AMT (888 л.с.)");
    }

    @Test
    public void converter_NullFromXmlFile_ThrowException() throws IOException {
        var xml = new String(getClass().getClassLoader().getResourceAsStream("carsNull.xml").readAllBytes());

        assertThrows(IllegalArgumentException.class,
                () -> xmlConverter.converterXml(xml, Catalog.class),
                "XML does not represent an instance of type:edu.klifanova.uniquevaluesinxml.model.Catalog" );
    }

    @Test
    public void converter_NotValidClassFromXmlFile_ThrowException() throws IOException {
        var xml = new String(getClass().getClassLoader().getResourceAsStream("cars.xml").readAllBytes());

        assertThrows(IllegalArgumentException.class,
                () -> xmlConverter.converterXml(xml, Mark.class),
                "XML does not represent an instance of type:edu.klifanova.uniquevaluesinxml.model.Mark");
    }
}
