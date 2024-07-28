package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

@Service
public class AffiliateDiscount implements Discount {
    @Override
    public double apply(Bill bill, User user) {

        // check if user is an affiliate
        if (!user.isAffiliate())
            return 0;

        double totalNonGroceryItems = bill.getTotalBillWithoutGrocery();
        return totalNonGroceryItems * 0.1;
    }
}
