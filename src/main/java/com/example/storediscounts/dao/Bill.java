package com.example.storediscounts.dao;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

/**
 * Represents a bill with a list of bill items.
 *
 * <p>
 * The {@code Bill} class provides methods to calculate the total bill amount
 * and the total bill amount excluding grocery items.
 * </p>
 *
 * <p>
 * This class uses Lombok annotations for boilerplate code generation:
 * {@link Data}, {@link AllArgsConstructor},
 * {@link Builder}.
 * </p>
 */

@Data
@AllArgsConstructor
@Builder
public class Bill {

    /**
     * The list of bill items.
     */
    private List<BillItem> items;

    /**
     * Calculates the total bill amount.
     *
     * @return the total amount of the bill
     */
    public final double getTotalBill() {
        return items
                .stream()
                .mapToDouble(BillItem::getPrice)
                .sum();
    }

    /**
     * Calculates the total bill amount excluding grocery items.
     *
     * @return the total amount of the bill without grocery items
     */
    public final double getTotalBillWithoutGrocery() {
        return  items
                .stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(BillItem::getPrice)
                .sum();
    }

}
