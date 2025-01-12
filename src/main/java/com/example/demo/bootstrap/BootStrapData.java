package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (partRepository.count() == 0 && productRepository.count() == 0) {

            // Catalytic Converter Part 1
            InhousePart catalyticConverter = new InhousePart();
            catalyticConverter.setName("Catalytic Converter");
            catalyticConverter.setPrice(440.99);
            catalyticConverter.setInv(4);
            catalyticConverter.setMinInv(1);
            catalyticConverter.setMaxInv(12);
            catalyticConverter.setPartId(1);
            inhousePartRepository.save(catalyticConverter);

            // Coil Springs Part 2
            InhousePart coilSprings = new InhousePart();
            coilSprings.setName("Coil Springs");
            coilSprings.setPrice(74.99);
            coilSprings.setInv(24);
            coilSprings.setMinInv(2);
            coilSprings.setMaxInv(100);
            coilSprings.setPartId(2);
            inhousePartRepository.save(coilSprings);

            // Alternator Part 3
            InhousePart alternator = new InhousePart();
            alternator.setName("Alternator");
            alternator.setPrice(745.99);
            alternator.setInv(4);
            alternator.setMinInv(1);
            alternator.setMaxInv(8);
            alternator.setPartId(3);
            inhousePartRepository.save(alternator);

            // Transmission Part 4
            OutsourcedPart transmission = new OutsourcedPart();
            transmission.setName("Transmission");
            transmission.setPrice(843.99);
            transmission.setInv(4);
            transmission.setMinInv(1);
            transmission.setMaxInv(10);
            transmission.setCompanyName("Wayne's Transmission Repair");
            outsourcedPartRepository.save(transmission);

            // Brake Pads Part 5
            OutsourcedPart brakePads = new OutsourcedPart();
            brakePads.setName("Brake Pads");
            brakePads.setPrice(99.99);
            brakePads.setInv(32);
            brakePads.setMinInv(10);
            brakePads.setMaxInv(200);
            brakePads.setCompanyName("Jerry's Automotive");
            outsourcedPartRepository.save(brakePads);

///////////////////////////////////////////////////////////////////////////////////////////////////////

            // All 5 products
            Product truck =  new Product("Tool Mans Truck", 45000.99, 5);
            Product sedan =  new Product("Street Race Sedan", 55000.99, 3);
            Product van = new Product("Momma Bear Minivan", 35000.99, 6);
            Product coupe = new Product("Cool Mans Coupe", 47899.99, 4);
            Product suv = new Product("Soccer Mom Suv", 67000.99, 5);
            productRepository.save(truck);
            productRepository.save(sedan);
            productRepository.save(van);
            productRepository.save(coupe);
            productRepository.save(suv);

            System.out.println("Sample inventory added to database.");
        } else {
            System.out.println("Database already contains data. No sample inventory added.");
        }





        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
