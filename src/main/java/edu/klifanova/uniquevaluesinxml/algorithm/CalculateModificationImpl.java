package edu.klifanova.uniquevaluesinxml.algorithm;

import edu.klifanova.uniquevaluesinxml.model.Catalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Slf4j
public class CalculateModificationImpl implements CalculateModification {

    @Override
    public int calculateModificationsNameCount(Catalog catalog) {
        log.debug("calculate modifications count for catalog");
        var setModificationName = new HashSet<String>();
        var listMark = catalog.getMarks();
        for (var mark : listMark) {
            var listFolder = mark.getFolders();
            for (var folder : listFolder) {
                var listModification = folder.getModifications();
                for (var modification : listModification) {
                    var nameModification = modification.getName();
                    setModificationName.add(nameModification);
                }
            }
        }
        return setModificationName.size();
    }
}
