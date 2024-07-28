package com.example.storediscounts.dao;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Represents an item in a bill.
 *
 * <p>
 * The {@code BillItem} class includes the name of the item, its price,
 * and whether it is a grocery item.
 * </p>
 *
 * <p>
 * This class uses Lombok annotations for boilerplate code generation:
 * {@link Data}, {@link AllArgsConstructor}, and {@link Builder}.
 * </p>
 */
@Data
@AllArgsConstructor
@Builder
public class BillItem {

    /**
     * The name of the bill item.
     */
    private String name;

    /**
     * The price of the bill item.
     */
    private double price;

    /**
     * Indicates whether the bill item is a grocery item.
     */
    private boolean grocery;

}
