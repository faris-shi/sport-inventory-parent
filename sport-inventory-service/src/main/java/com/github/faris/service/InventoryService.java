package com.github.faris.service;


import com.github.faris.service.model.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> getAllInventories();

    void addInventory(Inventory inventory);

    Inventory getInventoryById(Long id);
}
