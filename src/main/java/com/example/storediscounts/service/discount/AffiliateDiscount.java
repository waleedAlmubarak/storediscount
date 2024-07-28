package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

/**
 * Service for applying affiliate discounts to a bill.
 *
 * <p>
 * The {@code AffiliateDiscount} class calculates the discount applicable for
 * users who are affiliates. The discount is a percentage of the total amount
 * of non-grocery items in the bill.
 * </p>
 */
@Service
public class AffiliateDiscount implements Discount {

    /**
     * The discount rate for affiliates.
     *
     * <p>
     * This constant represents the percentage discount applied to the total
     * amount of non-grocery items in the bill for users who are affiliates.
     * The value is 10% (0.1).
     * </p>
     */
    public static final double DISCOUNT_RATE = 0.1;


    /**
     * Applies the affiliate discount to a given bill based on the user’s
     * status.
     *
     * <p>
     * If the user is an affiliate, a 10% discount is applied to the total
     * amount of non-grocery items in the bill. If the user is not an
     * affiliate, no discount is applied.
     * </p>
     *
     * @param bill the bill to which the discount is to be applied
     * @param user the user who is receiving the discount
     * @return the discount amount to be applied to the bill
     */
    @Override
    public final double apply(final Bill bill, final User user) {

        // check if user is an affiliate
        if (!user.isAffiliate()) {
            return 0;
        }

        // Calculate total amount of non-grocery items
        double totalNonGroceryItems = bill.getTotalBillWithoutGrocery();

        // Return 10% discount on the total amount of non-grocery items
        return totalNonGroceryItems * DISCOUNT_RATE;
    }
}
