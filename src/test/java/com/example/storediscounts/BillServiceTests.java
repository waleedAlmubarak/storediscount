package com.example.storediscounts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.BillItem;
import com.example.storediscounts.dao.User;
import com.example.storediscounts.service.bill.BillService;
import com.example.storediscounts.service.bill.BillServiceImpl;
import com.example.storediscounts.service.discount.AffiliateDiscount;
import com.example.storediscounts.service.discount.EmployeeDiscount;
import com.example.storediscounts.service.discount.FixedDiscount;
import com.example.storediscounts.service.discount.LoyalCustomerDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BillServiceTests {

    @InjectMocks
    EmployeeDiscount employeeDiscount;

    @InjectMocks
    AffiliateDiscount affiliateDiscount;

    @InjectMocks
    LoyalCustomerDiscount loyalCustomerDiscount;

    @InjectMocks
    FixedDiscount fixedDiscount;

    private BillService billService;

    User user;
    Bill bill;

    @BeforeEach
    public void setUp() {

        // init bill service
        billService = new BillServiceImpl(employeeDiscount, affiliateDiscount, loyalCustomerDiscount, fixedDiscount);

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
        // total: 188.77

    }

    @Test
    public void testNetPayableAmount() {

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals(129.57500000000002, netAmount);
    }

    @Test
    public void testNetPayableAmountWithFixedAmountOnly() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(false)
                .affiliate(false)
                .years(1)
                .build();

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals( 188.77 - 5, netAmount);
    }

    @Test
    public void testNetPayableAmountWithAffiliateDiscountOnly() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(false)
                .affiliate(true)
                .years(1)
                .build();

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals( 188.77 -  18.065 - 5, netAmount);
    }

    @Test
    public void testNetPayableAmountWithEmployeeDiscountOnly() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(true)
                .affiliate(false)
                .years(1)
                .build();

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals( 188.77 -  54.195 - 5, netAmount);
    }

    @Test
    public void testNetPayableAmountWithLoyalDiscountOnly() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(false)
                .affiliate(false)
                .years(2)
                .build();

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals( 188.77 -  9.0325 - 5, netAmount);
    }

    @Test
    public void testNetPayableAmountWithAllDiscounts() {

        // set up a user
        user = User.builder()
                .name("Waleed")
                .employee(true)
                .affiliate(true)
                .years(3)
                .build();

        double netAmount = billService.getNetPayableAmount(bill, user);
        assertEquals( 188.77 -  54.195 - 5, netAmount);
    }

}
