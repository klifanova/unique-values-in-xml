package edu.klifanova.uniquevaluesinxml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;


import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Folder {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "folder")
    private String  model;
    private Long model_id;
    @XmlElement(name = "modification")
    private List<Modification> modifications;
}
