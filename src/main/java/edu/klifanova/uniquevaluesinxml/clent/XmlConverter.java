package edu.klifanova.uniquevaluesinxml.clent;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
@Slf4j
public class XmlConverter {

    public <T> T converterXml(String fileName, Class<T> clazz) {
        log.debug("converter xml data");
        Object dataXml = null;
        try {
            var jaxbContext = JAXBContext.newInstance(clazz);
            var unMarshaller = jaxbContext.createUnmarshaller();
            dataXml = unMarshaller.unmarshal(new ByteArrayInputStream(fileName.getBytes()));
        } catch (JAXBException e) {
            log.error("Error reading file: ", e);

        }
        if (clazz.isInstance(dataXml)) {
            return clazz.cast(dataXml);
        } else {
            throw new IllegalArgumentException("XML does not represent an instance of type:" + clazz.getName());
        }
    }
}
