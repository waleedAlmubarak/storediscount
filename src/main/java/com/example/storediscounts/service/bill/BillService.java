package com.example.storediscounts.service.bill;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;

/**
 * Service interface for calculating bill amounts with discounts.
 *
 * <p>
 * The {@code BillService} interface defines methods for calculating the net
 * payable amount of a bill based on user details and applicable discounts.
 * </p>
 */
public interface BillService {

    /**
     * Calculates the net payable amount for a given bill and user.
     *
     * @param bill the bill for which the net payable amount is calculated
     * @param user the user whose discounts are applied to the bill
     * @return the net payable amount after applying discounts
     */
    double getNetPayableAmount(Bill bill, User user);
}
