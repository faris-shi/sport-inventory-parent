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
import java.util.List;

@RequestScoped
@Named
public class InventoryBean {

    @Size(min = 3, max = 50)
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
        return Inventory.builder().name(name).sport(sport).quantity(quantity).price(price).build();
    }

    public List<Inventory> getInventories() {
        return inventoryService.getAllInventories();
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
