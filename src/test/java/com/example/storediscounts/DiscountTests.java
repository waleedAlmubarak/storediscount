package com.example.storediscounts;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.BillItem;
import com.example.storediscounts.dao.User;
import com.example.storediscounts.service.discount.AffiliateDiscount;
import com.example.storediscounts.service.discount.EmployeeDiscount;
import com.example.storediscounts.service.discount.FixedDiscount;
import com.example.storediscounts.service.discount.LoyalCustomerDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DiscountTests {

    @InjectMocks
    EmployeeDiscount employeeDiscount;

    @InjectMocks
    AffiliateDiscount affiliateDiscount;

    @InjectMocks
    LoyalCustomerDiscount loyalCustomerDiscount;

    @InjectMocks
    FixedDiscount fixedDiscount;

    private User user;
    private Bill bill;

    @BeforeEach
    public void setUp() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(true)
                .affiliate(true)
                .years(2)
                .build();

        // set up a bill
        bill = new Bill(Arrays.asList(
                new BillItem("Tomato 2KG", 8.12, true),
                new BillItem("shampoo", 30, false),
                new BillItem("stocks", 50, false),
                new BillItem("printer", 100.65, false)
        ));
    }

    @Test
    public void testEmployeeDiscount() {
        double discount = employeeDiscount.apply(bill, user);
        assertEquals(54.195, discount);
    }

    @Test
    public void testNonEmployeeDiscount() {
        user.setEmployee(false);
        double discount = employeeDiscount.apply(bill, user);
        assertEquals(0, discount);
    }

    @Test
    public void testAffiliateDiscount() {
        double discount = affiliateDiscount.apply(bill, user);
        assertEquals(18.065, discount);
    }

    @Test
    public void testNonAffiliateDiscount() {
        user.setAffiliate(false);
        double discount = affiliateDiscount.apply(bill, user);
        assertEquals(0, discount);
    }

    @Test
    public void testLoyalDiscount() {
        double discount = loyalCustomerDiscount.apply(bill, user);
        assertEquals(9.0325, discount);
    }

    @Test
    public void testNonLoyalDiscount() {
        user.setYears(1.99);
        double discount = loyalCustomerDiscount.apply(bill, user);
        assertEquals(0, discount);
    }

    @Test
    public void testFixedDiscount() {
        double discount = fixedDiscount.apply(bill, user);
        assertEquals(5, discount);
    }

    @Test
    public void testFixedDiscountWithItems990() {
        bill = new Bill(List.of(
                new BillItem("item in example", 990, true)
        ));
        double discount = fixedDiscount.apply(bill, user);
        assertEquals(45, discount);
    }

    @Test
    public void testFixedDiscountWithItemsLessThan100() {
        bill = new Bill(List.of(
                new BillItem("item", 99.99, true)
        ));
        double discount = fixedDiscount.apply(bill, user);
        assertEquals(0, discount);
    }
}
