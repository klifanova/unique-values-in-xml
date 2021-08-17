package edu.klifanova.uniquevaluesinxml.client;


import edu.klifanova.uniquevaluesinxml.clent.CarsReader;
import edu.klifanova.uniquevaluesinxml.clent.XmlConverter;
import edu.klifanova.uniquevaluesinxml.model.Catalog;
import edu.klifanova.uniquevaluesinxml.model.Folder;
import edu.klifanova.uniquevaluesinxml.model.Mark;
import edu.klifanova.uniquevaluesinxml.model.Modification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarsReaderTest {

    @Mock
    private XmlConverter xmlConverter;
    @InjectMocks
    private CarsReader carsReader;

    @Test
    public void read_firstModificationName_ModificationName() throws IOException {
        var modificationNames = new ArrayList<String>();
        modificationNames.add("CCX Edition 4.8 AMT (888 л.с.)");
        modificationNames.add("4.7 MT (806 л.с.)");
        var folder = createFolder(modificationNames);
        var folders = new ArrayList<Folder>();
        folders.add(folder);
        var mark = createMark(folders);
        var marks = new ArrayList<Mark>();
        marks.add(mark);
        marks.add(mark);
        var catalog = createCatalog(marks);


        when(xmlConverter.converterXml(anyString(), eq(Catalog.class))).thenReturn(catalog);
        var catalogFromFile = carsReader.getCatalog("src/test/resources/carsForCarsReader.xml");

        assertEquals(catalogFromFile, catalog);
        var marksFromFile = catalogFromFile.getMarks();
        assertNotNull(marksFromFile);
        assertEquals(2, marksFromFile.size());
        var markFromFile = marksFromFile.get(0);
        assertNotNull(markFromFile);
        var foldersFromFile = markFromFile.getFolders();
        assertNotNull(foldersFromFile);
        assertEquals(1, foldersFromFile.size());
        var folderFromFile = foldersFromFile.get(0);
        assertNotNull(folderFromFile);
        var modificationsFromFile = folderFromFile.getModifications();
        assertNotNull(modificationsFromFile);
        assertEquals(2, modificationsFromFile.size());
        var modificationFromFile = modificationsFromFile.get(0);
        assertNotNull(modificationFromFile);
        var modificationNameFromFile = modificationFromFile.getName();
        assertNotNull(modificationNameFromFile);
        assertEquals(modificationNameFromFile, "CCX Edition 4.8 AMT (888 л.с.)");
        var markFromFileTwo = marksFromFile.get(1);
        assertNotNull(markFromFileTwo);
        var foldersFromFileTwo = markFromFileTwo.getFolders();
        assertNotNull(foldersFromFileTwo);
        assertEquals(1, foldersFromFileTwo.size());
        var folderFromFileTwo = foldersFromFileTwo.get(0);
        assertNotNull(folderFromFileTwo);
        var modificationsFromFileTwo = folderFromFileTwo.getModifications();
        assertNotNull(modificationsFromFileTwo);
        assertEquals(2, modificationsFromFileTwo.size());
        var modificationFromFileTwo = modificationsFromFileTwo.get(1);
        assertNotNull(modificationFromFileTwo);
        var modificationNameFromFileTwo = modificationFromFileTwo.getName();
        assertNotNull(modificationNameFromFileTwo);
        assertEquals(modificationNameFromFileTwo, "4.7 MT (806 л.с.)");
        verify(xmlConverter, times(1)).converterXml(anyString(), eq(Catalog.class));
    }

    @Test
    public void read_nullXmlFile_ThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> carsReader.getCatalog(null));
    }

    private Catalog createCatalog(List<Mark> marks) {
        var catalog = new Catalog();
        catalog.setMarks(marks);
        return catalog;
    }

    private Mark createMark(List<Folder> folders) {
        var mark = new Mark();
        mark.setFolders(folders);
        return mark;
    }

    private Folder createFolder(List<String> modificationNames) {
        var folder = new Folder();
        var listModification = new ArrayList<Modification>();
        for (int i = 0; i < modificationNames.size(); i++) {
            var modification = new Modification();
            modification.setName(modificationNames.get(i));
            listModification.add(modification);
        }
        folder.setModifications(listModification);
        return folder;
    }
}
