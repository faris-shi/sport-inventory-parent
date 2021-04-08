package com.github.faris.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i ORDER BY i.dateUpdated DESC")
public class Inventory implements Serializable {

    @Id
    @SequenceGenerator(name = "inventory_sequence", sequenceName = "inventory_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sport")
    private String sport;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_updated")
    private Date dateUpdated;

    @PrePersist
    @PreUpdate
    public void createdOrUpdateAt(){
        dateUpdated = new Date();
    }
}
