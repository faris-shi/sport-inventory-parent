package com.github.faris.sport.web;

import com.github.faris.service.InventoryService;
import com.github.faris.service.StoreService;
import com.github.faris.service.model.Inventory;
import com.github.faris.service.model.Store;
import com.github.faris.sport.web.interceptor.Logged;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestScoped
@Named
public class StoreBean {

    private Long id;

    @Size(min = 3, max = 10)
    private String name;

    @NotEmpty
    private String location;

    @Size(min = 1)
    private List<Long> inventoryIds = new ArrayList<>();

    @EJB
    private StoreService storeService;

    @EJB
    private InventoryService inventoryService;

    @Logged
    public void addStore() {
        storeService.addStore(buildStore());
    }

    @Logged
    public void updateStore() {
        storeService.updateStore(id, inventoryIds.stream().map(id -> inventoryService.getInventoryById(id)).collect(toList()));
    }

    private Store buildStore() {
        return Store.builder().id(id).name(name).location(location).inventories(
                inventoryIds.stream().map(id -> inventoryService.getInventoryById(id)).collect(toList())
        ).build();
    }

    public List<Store> getStores() {
        return storeService.getAllStores();
    }

    public void onInventoryChange() {
        inventoryIds = storeService.getStoreById(id).getInventories().stream().map(Inventory::getId).collect(toList());
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }

    public void setInventoryIds(List<Long> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }
}
