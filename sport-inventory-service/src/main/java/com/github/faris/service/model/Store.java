package com.github.faris.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
public class Store implements Serializable {

    @Id
    @SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Inventory> inventories;
}
