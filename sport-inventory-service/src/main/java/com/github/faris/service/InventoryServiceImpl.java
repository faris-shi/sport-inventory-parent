package com.github.faris.service;


import com.github.faris.service.model.Inventory;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {

    private static final List<Inventory> INVENTORIES = new ArrayList<>();

    private static final AtomicLong COUNTER = new AtomicLong(1);

    @Override
    public List<Inventory> getAllInventories() {
        return INVENTORIES;
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return INVENTORIES.stream().filter(inventory -> inventory.getId().equals(id)).findFirst().get();
    }

    @Override
    public void addInventory(Inventory inventory) {
        inventory.setId(COUNTER.incrementAndGet());
        INVENTORIES.add(inventory);
    }
}
