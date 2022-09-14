package com.example.obspringlaptop.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@ApiModel("Entidad laptop para representar ordenadores")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Llave primaria auto incremental (Long)")
    private Long id;
    @ApiModelProperty("Marca de la laptop")
    private String brand;
    @ApiModelProperty("Modelo de la laptop")
    private String model;
    @ApiModelProperty("Tamaño de la pantalla")
    private Double screenSize;
    @ApiModelProperty("Información del procesador")
    private String processor;
    @ApiModelProperty("Cantidad de memoria RAM")
    private Integer ram;
    @ApiModelProperty("Espacio de almacenamiento en disco duro")
    private Integer storage;
    @ApiModelProperty("Sistema Operativo")
    private String operatingSystem;
    @ApiModelProperty("Año de lanzamiento")
    private Integer releaseYear;
    @ApiModelProperty("Precio de la laptop en dólares")
    private Double price;

    public Laptop() {
    }

    public Laptop(Long id, String brand, String model, Double screenSize, String processor, Integer ram, Integer storage, String operatingSystem, Integer releaseYear, Double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
