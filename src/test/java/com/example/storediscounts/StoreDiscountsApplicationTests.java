package com.example.storediscounts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreDiscountsApplicationTests {

    @Test
    void contextLoads() {

        List<Double> discounts = List.of(
                40.0,
                30.0,
                10.0);

        // get the max discount,
        // otherwise return 0
        double maxPercentageDiscount = discounts
                .stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);

        assertEquals(40.0, maxPercentageDiscount);

    }

}
