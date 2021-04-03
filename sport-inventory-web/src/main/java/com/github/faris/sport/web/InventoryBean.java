package com.github.faris.sport.web;

import com.github.faris.service.InventoryService;
import com.github.faris.service.model.Inventory;
import com.github.faris.sport.web.interceptor.Logged;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class InventoryBean {

    private Long id;

    @Size(min = 3, max = 10)
    private String name;

    @NotEmpty
    private String sport;

    @PositiveOrZero
    private Integer quantity;

    @Positive
    private BigDecimal price;

    @EJB
    private InventoryService inventoryService;

    @Logged
    public void addInventory() {
        inventoryService.addInventory(buildInventory());
    }

    private Inventory buildInventory() {
        return Inventory.builder().id(id).name(name).sport(sport).
                quantity(quantity).price(price).dateUpdated(new Date()).build();
    }

    public List<Inventory> getInventories() {
        return inventoryService.getAllInventories();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}