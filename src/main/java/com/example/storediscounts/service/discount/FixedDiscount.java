package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

@Service
public class FixedDiscount implements Discount {
    @Override
    public double apply(Bill bill, User user) {
        return (Math.floor(bill.getTotalBill()/100))*5;
    }
}
