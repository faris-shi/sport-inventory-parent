package com.github.faris.service;

import com.github.faris.service.model.Inventory;
import com.github.faris.service.model.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Store> getAllStores() {
        return em.createNamedQuery("Store.findAll", Store.class).getResultList();
    }

    @Override
    public Store getStoreById(Long id) {
        return em.find(Store.class, id);
    }

    @Override
    public void addStore(Store store) {
        em.persist(store);
    }

    @Override
    public void updateStore(Long id, List<Long> inventoryIds) {
        Store store = em.find(Store.class, id);
        store.setInventories(
                inventoryIds.stream().map(i -> em.find(Inventory.class, i)).collect(toList())
        );
        em.merge(store);
    }
}
