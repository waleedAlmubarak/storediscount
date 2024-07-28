package com.example.storediscounts.dao;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BillItem {

    private String name;
    private double price;
    private boolean grocery;

}
