package com.example.obspringlaptop.controllers;

import com.example.obspringlaptop.entities.Laptop;
import com.example.obspringlaptop.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    LaptopRepository repository;
    @Value("${app.message}")
    String message;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
        System.out.println(message);
    }

    /**
     * Obtener todas las laptops de la base de datos
     * http://localhost:8081/api/laptops
     * @return el listado de las laptos de la base de datos
     */
    @GetMapping("/api/laptops")
    @ApiOperation("Obtener todas las laptops de la base de datos")
    public List<Laptop> findAll() {
        return repository.findAll();
    }

    /**
     * Obtener una laptop de la base de datos por su identificador
     * @param id, identificador de la laptop a buscar en la base de datos
     * @return ResponseEntity de Laptop con ok en caso de que la laptop exista en la base de
     *         datos, y un notFound en caso de que no se logre encontrar la laptop con el
     *         identificador recibido
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Obtener una laptop por su llave primaria (Long id)")
    public ResponseEntity<Laptop> findById(@ApiParam("Llave primaria tipo Long") @PathVariable Long id) {
        Optional<Laptop> laptopOpt = repository.findById(id);
        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Crear una laptop en la base de datos
     * http://localhost:8081/api/laptops
     * @param laptop, objeto que contiene los datos de la laptop a crear en la base de datos
     * @return ResponseEntity de Laptop con badRequest en caso de que se intente insertar
     *         una laptop con un identificador en la base de datos, y un ok con la laptop
     *         insertada en la base de datos
     */
    @PostMapping("/api/laptops")
    @ApiOperation("Crear una laptop en la base de datos")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if (laptop.getId() != null) {
            log.warn("Trying to create a laptop with id");
            ResponseEntity.badRequest().build();
        }
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Actualizar una laptop existente en la base de datos
     * http://localhost:8081/api/laptops
     * @param laptop, Laptop que será actualizada en la base de datos
     * @return, ResponseEntity de Laptop con badRequest en caso de que se intente actualizar
     *          una laptop que no exista en la base de datos, notFound si no existe la laptop
     *          con el identificador enviado, y un ok con la laptop actualizada en la base de datos
     */
    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar una laptop existente en la base de datos")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {
            log.warn("Trying to update a non existing laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!repository.existsById(laptop.getId())) {
            log.warn("Trying to update a non existing laptop");
            return ResponseEntity.notFound().build();
        }
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Eliminar una Laptop de la base de datos
     * http://localhost:8081/api/laptops/id
     * @param id, identificador de la laptop que será eliminada de la base de datos
     * @return, ResponseEntity de Laptop con notFound en caso de que no exista la laptop
     *          o bien noContent cuando se elimina la laptop de la base de datos
     */
    @ApiIgnore
    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Eliminar una laptop de la base de datos por su identificador")
    public ResponseEntity<Laptop> delete(@ApiParam("Llave primaria tipo Long")@PathVariable Long id) {
        if (!repository.existsById(id)) {
            log.warn("Trying to delete a non existing laptop");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Eliminar todos los Books de la base de datos
     * http://localhost:8081/api/laptops
     * @return, ResponseEntity de Laptop noContent cuando se eliminan todas las laptops
     *          de la base de datos
     */
    @ApiIgnore
    @DeleteMapping("/api/laptops")
    @ApiOperation("Eliminar todas las laptops de la base de datos")
    public ResponseEntity<Laptop> deleteAll() {
        log.info("REST Request for delete all laptops");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
