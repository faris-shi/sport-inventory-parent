package com.github.faris.service;


import com.github.faris.service.model.Inventory;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Inventory> getAllInventories() {
        return em.createNamedQuery("Inventory.findAll", Inventory.class).getResultList();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return em.find(Inventory.class, id);
    }

    @Override
    public void addInventory(Inventory inventory) {
        em.persist(inventory);
    }
}
