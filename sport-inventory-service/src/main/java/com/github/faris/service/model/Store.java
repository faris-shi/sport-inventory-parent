package com.github.faris.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store implements Serializable {

    private Long id;

    private String name;

    private String location;

    private List<Inventory> inventories;
}
