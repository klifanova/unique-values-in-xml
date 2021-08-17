package edu.klifanova.uniquevaluesinxml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;


import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Mark {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "folder", nillable = true)
    private List<Folder> folders;
    @XmlElement(name = "code")
    private String code;
}
