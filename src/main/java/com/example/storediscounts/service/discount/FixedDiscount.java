package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

/**
 * Service for applying a fixed discount to a bill based on the total amount.
 *
 * <p>
 * The {@code FixedDiscount} class calculates a fixed discount amount based
 * on the total bill amount. For every specified amount in the bill, a fixed
 * discount is applied.
 * </p>
 */
@Service
public class FixedDiscount implements Discount {

    /**
     * The amount threshold for applying the fixed discount.
     *
     * <p>
     * This constant represents the amount of the bill for which the fixed
     * discount is applied. For every multiple of this amount in the total bill,
     * a discount is applied. The value is 100.
     * </p>
     */
    public static final double FOR_EVERY_AMOUNT = 100;

    /**
     * The fixed discount amount applied for every threshold amount.
     *
     * <p>
     * This constant represents the discount amount applied for every multiple
     * of the {@link #FOR_EVERY_AMOUNT} in the total bill. The value is 5.
     * </p>
     */
    public static final double DISCOUNT_AMOUNT = 5;

    /**
     * Applies a fixed discount to a given bill based on the total bill amount.
     *
     * <p>
     * The discount amount is calculated based on the total bill amount.
     * For every specified amount in the total bill, the fixed discount is
     * applied. The total discount is the floor value of the total bill divided
     * by {@link #FOR_EVERY_AMOUNT} multiplied by {@link #DISCOUNT_AMOUNT}.
     * </p>
     *
     * @param bill the bill to which the discount is to be applied
     * @param user the user who is receiving the discount (not used in this
     *             case)
     * @return the fixed discount amount to be applied to the bill
     */
    @Override
    public final double apply(final Bill bill, final User user) {
        return ((Math.floor(bill.getTotalBill() / FOR_EVERY_AMOUNT))
                * DISCOUNT_AMOUNT);
    }
}
