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
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequestScoped
@Named
public class StoreBean {

    private Long id;

    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty
    private String location;

    @Size(min = 1)
    private List<Long> addInventoryIds = new ArrayList<>();

    private List<Long> updateInventoryIds = new ArrayList<>();

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
        storeService.updateStore(id, updateInventoryIds);
    }

    private Store buildStore() {
        return Store.builder().name(name).location(location).inventories(
                addInventoryIds.stream().map(id -> inventoryService.getInventoryById(id)).collect(toList())
        ).build();
    }

    public List<Store> getStores() {
        return storeService.getAllStores();
    }

    /**
     * get all inventories which can be selected in the add form
     *
     * @return return the unselected inventories.
     */
    public List<Inventory> getUnSelectedInventories() {
        Set<Long> ids = getStores().stream().flatMap(store -> storeService.getStoreById(store.getId()).getInventories().stream())
                .map(Inventory::getId).collect(Collectors.toSet());
        return inventoryService.getAllInventories().stream().filter(in -> !ids.contains(in.getId())).collect(toList());
    }

    public void onInventoryChange() {
        if (id == null) {
            updateInventoryIds.clear();
            return;
        }
        updateInventoryIds = storeService.getStoreById(id).getInventories().stream().map(Inventory::getId).collect(toList());
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

    public List<Long> getAddInventoryIds() {
        return addInventoryIds;
    }

    public List<Long> getUpdateInventoryIds() {
        return updateInventoryIds;
    }

    public void setAddInventoryIds(List<Long> addInventoryIds) {
        this.addInventoryIds = addInventoryIds;
    }

    public void setUpdateInventoryIds(List<Long> updateInventoryIds) {
        this.updateInventoryIds = updateInventoryIds;
    }
}
