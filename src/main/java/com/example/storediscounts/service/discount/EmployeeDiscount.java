package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import org.springframework.stereotype.Service;

/**
 * Service for applying employee discounts to a bill.
 *
 * <p>
 * The {@code EmployeeDiscount} class calculates the discount applicable
 * for users who are employees. The discount is a percentage of the total
 * amount of non-grocery items in the bill.
 * </p>
 */
@Service
public class EmployeeDiscount implements Discount {

    /**
     * The discount rate for employees.
     *
     * <p>
     * This constant represents the percentage discount applied to the total
     * amount of non-grocery items in the bill for users who are employees.
     * The value is 30% (0.3).
     * </p>
     */
    public static final double DISCOUNT_RATE = 0.3;

    /**
     * Applies the employee discount to a given bill based on the user’s
     * status.
     *
     * <p>
     * If the user is an employee, a discount equal to {@link #DISCOUNT_RATE}
     * of the total amount of non-grocery items in the bill is applied. If the
     * user is not an employee, no discount is applied.
     * </p>
     *
     * @param bill the bill to which the discount is to be applied
     * @param user the user who is receiving the discount
     * @return the discount amount to be applied to the bill
     */
    @Override
    public final double apply(final Bill bill, final User user) {

        // check if user is an employee
        if (!user.isEmployee()) {
            return 0;
        }

        // Calculate total amount of non-grocery items
        double totalNonGroceryItems = bill.getTotalBillWithoutGrocery();

        // Return the discount amount based on DISCOUNT_RATE
        return totalNonGroceryItems * DISCOUNT_RATE;
    }
}
