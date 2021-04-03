package com.github.faris.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory implements Serializable {

    private Long id;

    private String name;

    private String sport;

    private Integer quantity;

    private BigDecimal price;

    private Date dateUpdated;
}
