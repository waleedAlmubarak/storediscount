package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

@Service
public class LoyalCustomerDiscount implements Discount {
    @Override
    public double apply(Bill bill, User user) {

        // check if user is loyal (has 2 or more years)
        if (user.getYears() < 2)
            return 0;

        double totalNonGroceryItems = bill.getTotalBillWithoutGrocery();
        return totalNonGroceryItems * 0.05;
    }
}
