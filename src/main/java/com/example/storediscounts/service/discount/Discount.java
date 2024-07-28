package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;

public interface Discount {
    double apply(Bill bill, User user);
}
