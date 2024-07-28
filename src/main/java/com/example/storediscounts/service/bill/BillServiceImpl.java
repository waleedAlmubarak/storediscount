package com.example.storediscounts.service.bill;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import com.example.storediscounts.service.discount.EmployeeDiscount;
import com.example.storediscounts.service.discount.AffiliateDiscount;
import com.example.storediscounts.service.discount.LoyalCustomerDiscount;
import com.example.storediscounts.service.discount.FixedDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of {@link BillService} for calculating the net payable amount
 * of a bill after applying various discounts.
 *
 * <p>
 * The {@code BillServiceImpl} class applies employee, affiliate, loyal
 * customer, and fixed discounts to a bill and calculates the final amount
 * payable by the user.
 * </p>
 */

@Service
public class BillServiceImpl implements BillService {

    /**
     * Service for applying employee discounts.
     */
    private final EmployeeDiscount employeeDiscount;

    /**
     * Service for applying affiliate discounts.
     */
    private final AffiliateDiscount affiliateDiscount;

    /**
     * Service for applying loyal customer discounts.
     */
    private final LoyalCustomerDiscount loyalCustomerDiscount;

    /**
     * Service for applying fixed discounts.
     */
    private final FixedDiscount fixedDiscount;

    /**
     * Constructs a {@code BillServiceImpl} with the specified discount
     * services.
     *
     * @param employeeDiscount the service for applying employee discounts
     * @param affiliateDiscount the service for applying affiliate discounts
     * @param loyalCustomerDiscount the service for applying loyal customer
     *                              discounts
     * @param fixedDiscount the service for applying fixed discounts
     */
    @Autowired
    public BillServiceImpl(final EmployeeDiscount employeeDiscount,
                           final AffiliateDiscount affiliateDiscount,
                           final LoyalCustomerDiscount loyalCustomerDiscount,
                           final FixedDiscount fixedDiscount) {
        this.employeeDiscount = employeeDiscount;
        this.affiliateDiscount = affiliateDiscount;
        this.loyalCustomerDiscount = loyalCustomerDiscount;
        this.fixedDiscount = fixedDiscount;
    }

    /**
     * Calculates the net payable amount for a given bill and user after
     * applying various discounts.
     *
     * <p>
     * This method applies employee, affiliate, and loyal customer discounts
     * and calculates the maximum percentage discount. It also applies a fixed
     * discount and returns the total amount payable after deducting these
     * discounts from the total bill.
     * </p>
     *
     * @param bill the bill to calculate the net payable amount for
     * @param user the user for whom the discounts are applied
     * @return the net payable amount after applying discounts
     */
    @Override
    public final double getNetPayableAmount(final Bill bill,
                                            final User user) {

        double employeeDiscountAmount = employeeDiscount
                .apply(bill, user);

        double affiliateDiscountAmount = affiliateDiscount
                .apply(bill, user);

        double loyalCustomerDiscountAmount = loyalCustomerDiscount
                .apply(bill, user);

        // Get all percentage discounts and add them to the list.
        // This allows finding the maximum discount among them.
        List<Double> discounts = List.of(
                employeeDiscountAmount,
                affiliateDiscountAmount,
                loyalCustomerDiscountAmount);

        // Find the maximum percentage discount; otherwise return 0.
        double maxPercentageDiscount = discounts
                .stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);

        // get fixed discount
        double fixedDiscountAmount = fixedDiscount.apply(bill, user);

        // return net payable amount
        return (bill.getTotalBill()
                - maxPercentageDiscount
                - fixedDiscountAmount);

    }
}
