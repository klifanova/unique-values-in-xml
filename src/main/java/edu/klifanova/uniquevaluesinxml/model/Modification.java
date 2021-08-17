package edu.klifanova.uniquevaluesinxml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Modification {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "id")
    private Long id;
    private String mark_id;
    private String folder_id;
    private String modification_id;
    private Long configuration_id;
    private Long tech_param_id;
    private String body_type;
    private String years;
    private String complectations;
}
