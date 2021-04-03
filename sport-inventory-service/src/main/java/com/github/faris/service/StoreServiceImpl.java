package com.github.faris.service;


import com.github.faris.service.model.Inventory;
import com.github.faris.service.model.Store;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {

    private static final Map<Long, Store> STORES = new HashMap<>();

    private static final AtomicLong COUNTER = new AtomicLong(1);

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(STORES.values());
    }

    @Override
    public Store getStoreById(Long id) {
        return STORES.get(id);
    }

    @Override
    public void addStore(Store store) {
        store.setId(COUNTER.incrementAndGet());
        STORES.put(store.getId(), store);
    }

    @Override
    public void updateStore(Long id, List<Inventory> inventories) {
        STORES.get(id).setInventories(inventories);
    }
}
