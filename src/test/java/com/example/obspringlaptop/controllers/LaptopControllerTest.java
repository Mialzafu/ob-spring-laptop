package com.example.obspringlaptop.controllers;

import com.example.obspringlaptop.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        addLaptops();
    }

    /*********************************************************************************************/
    // Métodos para insertar laptops y tener información en la base de datos
    /*********************************************************************************************/
    void addLaptops() {
        String json = """
                {
                    "brand": "HP",
                    "model": "HP15",
                    "screenSize": 15.6,
                    "processor": "Core i5-1135G7",
                    "ram": 16,
                    "storage": 1024,
                    "operatingSystem": "Windows 11",
                    "releaseYear": 2021,
                    "price": 800.0
                }
                """;
        create(json);
        json = """
                {
                    "brand": "Apple",
                    "model": "McBook Air",
                    "screenSize": 13.6,
                    "processor": "Apple M2",
                    "ram": 8,
                    "storage": 256,
                    "operatingSystem": "Mac Os",
                    "releaseYear": 2022,
                    "price": 1200.0
                }
                """;
        create(json);
        json = """
                {
                    "brand": "Apple",
                    "model": "McBook Air",
                    "screenSize": 13.6,
                    "processor": "Apple M2",
                    "ram": 8,
                    "storage": 256,
                    "operatingSystem": "Mac Os",
                    "releaseYear": 2022,
                    "price": 1200.0
                }
                """;
        create(json);

        json = """
                {
                    "brand": "Asus",
                    "model": "VivoBook",
                    "screenSize": 15.6,
                    "processor": "Core i3-1005G",
                    "ram": 12,
                    "storage": 512,
                    "operatingSystem": "Windows 11",
                    "releaseYear": 2022,
                    "price": 530.0
                }
                """;
        create(json);

        json = """
                {
                    "brand": "Acer",
                    "model": "Aspire 5 Slim",
                    "screenSize": 15.6,
                    "processor": "Apple M2",
                    "ram": 8,
                    "storage": 128,
                    "operatingSystem": "Windows 11",
                    "releaseYear": 2022,
                    "price": 400.0
                }
                """;
        create(json);
    }

    void create(String json) {
        // Preparación de los Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Preparar la petición
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Ejecutar la petición
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
    }
    /*********************************************************************************************/


    /*********************************************************************************************/
    // Pruebas unitarias generadas desde la clase LaptopController
    /*********************************************************************************************/
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(200, response.getStatusCodeValue());

        // Aserciones
        if (response.getBody() != null) {
            List<Laptop> books = Arrays.asList(response.getBody());
            assertTrue(books.size() > 0);
        }
    }

    @Test
    void findById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/10", Laptop.class);

        // Aserciones
        //assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertNotNull(response.getBody());
    }

    @Test
    void create() {
        // Preparación de los Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Creación del json
        String json = """
                {
                    "brand": "Lenovo",
                    "model": "IdeaPad",
                    "screenSize": 15.6,
                    "processor": "Core i5-1135G7",
                    "ram": 20,
                    "storage": 512,
                    "operatingSystem": "Windows 11",
                    "releaseYear": 2021,
                    "price": 750.0
                }
                """;

        // Preparar la petición
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Ejecutar la petición
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        // Aserciones
        if (response.getBody() != null) {
            Laptop result = response.getBody();
            assertEquals(6, result.getId());
            assertEquals("Lenovo", result.getBrand());
            assertEquals("IdeaPad", result.getModel());
        }
    }

    @Test
    void update() {
        // Preparación de los Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Creación del json
        String json = """
                {
                    "id": 6,
                    "brand": "Lenovo",
                    "model": "IdeaPad",
                    "screenSize": 15.6,
                    "processor": "Core i5-1135G7",
                    "ram": 20,
                    "storage": 1024,
                    "operatingSystem": "Windows 11",
                    "releaseYear": 2022,
                    "price": 800.0
                }
                """;

        // Preparar la petición
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        // Ejecutar la petición
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, Laptop.class);

        // Aserciones
        if (response.getBody() != null) {
            Laptop result = response.getBody();
            assertEquals(1024, result.getStorage());
            assertEquals(2022, result.getReleaseYear());
        }
    }

    @Test
    void delete() {
        // Preparación de los Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Preparar la petición
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Ejecutar la petición
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, request, Laptop.class);

        // Aserciones
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAll() {
        // Preparación de los Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Preparar la petición
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Ejecutar la petición
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request, Laptop.class);

        // Aserciones
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    /*********************************************************************************************/
}