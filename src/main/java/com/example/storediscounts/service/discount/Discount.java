package com.example.storediscounts.service.discount;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;

/**
 * Interface for applying discounts to a bill.
 *
 * <p>
 * The {@code Discount} interface defines a method for calculating the discount
 * amount based on a given bill and user. Implementing classes should provide
 * specific discount logic.
 * </p>
 */
public interface Discount {
    /**
     * Applies a discount to a given bill based on the user’s details.
     *
     * <p>
     * The discount calculation logic is defined in the implementation of this
     * method. Implementing classes should determine the discount amount based
     * on the provided {@link Bill} and {@link User} instances.
     * </p>
     *
     * @param bill the bill to which the discount is to be applied
     * @param user the user for whom the discount is being calculated
     * @return the discount amount to be applied to the bill
     */
    double apply(Bill bill, User user);
}
