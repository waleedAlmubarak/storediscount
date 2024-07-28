package com.example.storediscounts.service.bill;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;
import com.example.storediscounts.service.discount.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final EmployeeDiscount employeeDiscount;
    private final AffiliateDiscount affiliateDiscount;
    private final LoyalCustomerDiscount loyalCustomerDiscount;
    private final FixedDiscount fixedDiscount;

    @Autowired
    public BillServiceImpl(EmployeeDiscount employeeDiscount, AffiliateDiscount affiliateDiscount,
                           LoyalCustomerDiscount loyalCustomerDiscount, FixedDiscount fixedDiscount) {
        this.employeeDiscount = employeeDiscount;
        this.affiliateDiscount = affiliateDiscount;
        this.loyalCustomerDiscount = loyalCustomerDiscount;
        this.fixedDiscount = fixedDiscount;
    }

    @Override
    public double getNetPayableAmount(Bill bill, User user) {

        double employeeDiscountAmount = employeeDiscount.apply(bill, user);
        double affiliateDiscountAmount = affiliateDiscount.apply(bill, user);
        double loyalCustomerDiscountAmount = loyalCustomerDiscount.apply(bill, user);

        // get all percentage discounts and add them to list,
        // so we can get the max discount
        // cons:: you have to run through the all three processes every time bill id calculated (specially if the discount service has a heavy processing...).
        // Ideal best scenario is to run the discount service only if applicable, in that case you do not need to array.
        List<Double> discounts = List.of(
                employeeDiscountAmount,
                affiliateDiscountAmount,
                loyalCustomerDiscountAmount);

        // get the max discount,
        // otherwise return 0
        double maxPercentageDiscount = discounts
                .stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);

        // get fixed discount
        double fixedDiscountAmount = fixedDiscount.apply(bill, user);

        // return net payable amount
        return bill.getTotalBill() - maxPercentageDiscount - fixedDiscountAmount;

    }
}
