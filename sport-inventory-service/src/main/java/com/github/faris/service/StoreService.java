package com.github.faris.service;


import com.github.faris.service.model.Inventory;
import com.github.faris.service.model.Store;

import java.util.List;

public interface StoreService {

    List<Store> getAllStores();

    Store getStoreById(Long id);

    void addStore(Store store);

    void updateStore(Long id, List<Inventory> inventories);
}
