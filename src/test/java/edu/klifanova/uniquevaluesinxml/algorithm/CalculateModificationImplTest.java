package edu.klifanova.uniquevaluesinxml.algorithm;

import edu.klifanova.uniquevaluesinxml.model.Catalog;
import edu.klifanova.uniquevaluesinxml.model.Folder;
import edu.klifanova.uniquevaluesinxml.model.Mark;
import edu.klifanova.uniquevaluesinxml.model.Modification;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculateModificationImplTest {

    private CalculateModification calculateModification = new CalculateModificationImpl();

    @Test
    public void calculate_doubleNameAndDoubleMark_countModificationName(){
        var modificationNames = new ArrayList<String>();
        modificationNames.add("CCX Edition 4.8 AMT (888 л.с.)");
        modificationNames.add("CCX Edition 4.8 AMT (888 л.с.)");
        modificationNames.add("4.7 MT (806 л.с.)");
        var folder = createFolder(modificationNames);
        var listFolder = new ArrayList<Folder>();
        listFolder.add(folder);
        var mark = createMark(listFolder);
        var listMark = new ArrayList<Mark>();
        listMark.add(mark);
        listMark.add(mark);
        var catalog = createCatalog(listMark);

        var countName = calculateModification.calculateModificationsNameCount(catalog);

        assertEquals(countName,2);
    }

    private Catalog createCatalog(List<Mark> marks){
        var catalog = new Catalog();
        catalog.setMarks(marks);
        return catalog;
    }
    private Mark createMark(List<Folder> folders){
        var mark = new Mark();
        mark.setFolders(folders);
        return mark;
    }
    private Folder createFolder(List<String> modificationNames){
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
