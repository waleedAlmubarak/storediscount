package com.example.storediscounts.dao;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bill {

    private List<BillItem> items;

    public double getTotalBill() {
        return items
                .stream()
                .mapToDouble(BillItem::getPrice)
                .sum();
    }

    public double getTotalBillWithoutGrocery() {
        return  items
                .stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(BillItem::getPrice)
                .sum();
    }

}
