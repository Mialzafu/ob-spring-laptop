package com.example.obspringlaptop;

import com.example.obspringlaptop.entities.Laptop;
import com.example.obspringlaptop.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringLaptopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObSpringLaptopApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "HP", "HP 15", 15.6, "Core i5-1135G7", 16, 1024, "Windows 11", 2021, 800.00);
		Laptop laptop2 = new Laptop(null, "Apple", "McBook Air", 13.6, "Apple M2", 8, 256, "Mac Os", 2022, 1200.00);
		Laptop laptop3 = new Laptop(null, "Asus", "VivoBook", 15.6, "Core i3-1005G1", 12, 512, "Windows 11", 2022, 530.00);
		Laptop laptop4 = new Laptop(null, "Acer", "Aspire 5 Slim", 15.6, "Core i3-1115G4", 8, 128, "Windows 11", 2022, 400.00);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);
		repository.save(laptop4);

		System.out.println("Cantidad laptops en base de datos: " + repository.findAll().size());
	}

}
