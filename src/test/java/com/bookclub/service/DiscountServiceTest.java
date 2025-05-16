package com.bookclub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DiscountServiceTest {

    DiscountService discountService = new DiscountService();

    @Test
    void shouldApplyTenPercentDiscount() {
        double result = discountService.applyDiscount(100.0, 0.10);
        assertEquals(90.0, result);
    }

    @Test
    void shouldThrowExceptionForNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            discountService.applyDiscount(-50.0, 0.20);
        });
    }
}
