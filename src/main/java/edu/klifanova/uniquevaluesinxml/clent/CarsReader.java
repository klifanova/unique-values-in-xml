package edu.klifanova.uniquevaluesinxml.clent;

import edu.klifanova.uniquevaluesinxml.model.Catalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
@Slf4j
public class CarsReader {

    private XmlConverter xmlConverter;

    public CarsReader(XmlConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    public Catalog getCatalog(String file) throws IOException {
        log.debug("read file: {}", file);
        Catalog catalog = null;

        if (file != null) {
            var fis = new FileInputStream(file);
            var xmlString = StreamUtils.copyToString(fis, Charset.defaultCharset());
            catalog = xmlConverter.converterXml(xmlString, Catalog.class);
        } else {
            log.error("File don't have data");
            throw new IllegalArgumentException();
        }
        return catalog;
    }
}
