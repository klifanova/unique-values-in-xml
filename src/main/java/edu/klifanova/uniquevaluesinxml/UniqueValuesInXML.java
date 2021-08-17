package edu.klifanova.uniquevaluesinxml;

import edu.klifanova.uniquevaluesinxml.algorithm.CalculateModification;
import edu.klifanova.uniquevaluesinxml.clent.CarsReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class UniqueValuesInXML implements CommandLineRunner {

    private CalculateModification calculateModification;
    private CarsReader carsReader;

    public UniqueValuesInXML(CalculateModification calculateModification,
                             CarsReader carsReader) {
        this.calculateModification = calculateModification;
        this.carsReader = carsReader;
    }

    public static void main(String[] args) {
        SpringApplication.run(UniqueValuesInXML.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        log.info("Starting the Application");
        var catalog = carsReader.getCatalog(args[0]);
        var count = calculateModification.calculateModificationsNameCount(catalog);
        System.out.println(count);
        log.info("Closed the Application");
    }
}
