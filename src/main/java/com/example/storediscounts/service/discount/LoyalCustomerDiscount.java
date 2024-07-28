package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

/**
 * Service for applying loyalty discounts to a bill based on the user's
 * loyalty status.
 *
 * <p>
 * The {@code LoyalCustomerDiscount} class calculates a discount applicable
 * for users who have been loyal customers for a specified number of years.
 * The discount is a percentage of the total amount of non-grocery items in
 * the bill.
 * </p>
 */
@Service
public class LoyalCustomerDiscount implements Discount {

    /**
     * The minimum number of years a user must be loyal to qualify for the
     * discount.
     *
     * <p>
     * This constant represents the minimum number of years a user must have
     * been a loyal customer to be eligible for the discount. The value is
     * 2 years.
     * </p>
     */
    public static final double MIN_LOYALTY_YEARS = 2;

    /**
     * The discount rate for loyal customers.
     *
     * <p>
     * This constant represents the percentage discount applied to the total
     * amount of non-grocery items in the bill for users who have met the
     * loyalty criteria. The value is 5% (0.05).
     * </p>
     */
    public static final double DISCOUNT_RATE = 0.05;

    /**
     * Applies the loyalty discount to a given bill based on the user’s loyalty
     * status.
     *
     * <p>
     * If the user has been a loyal customer for at least
     * {@link #MIN_LOYALTY_YEARS} years, a discount equal to
     * {@link #DISCOUNT_RATE} of the total amount of non-grocery items in
     * the bill is applied. If the user does not meet the loyalty criteria,
     * no discount is applied.
     * </p>
     *
     * @param bill the bill to which the discount is to be applied
     * @param user the user who is receiving the discount
     * @return the discount amount to be applied to the bill
     */
    @Override
    public final double apply(final Bill bill, final User user) {

        // check if user is loyal (has 2 or more years)
        if (user.getYears() < MIN_LOYALTY_YEARS) {
            return 0;
        }

        // Calculate total amount of non-grocery items
        double totalNonGroceryItems = bill.getTotalBillWithoutGrocery();

        // Return the discount amount based on DISCOUNT_RATE
        return totalNonGroceryItems * DISCOUNT_RATE;
    }
}
