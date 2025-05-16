package com.bookclub.service;

/**
 * DiscountService provides utility methods for applying discounts to prices.
 */
public class DiscountService {

    /**
     * Applies a discount to a given price.
     *
     * @param price        the original price (must be >= 0)
     * @param discountRate the discount rate as a decimal (e.g., 0.10 for 10%)
     * @return the price after the discount
     * @throws IllegalArgumentException if price is negative or discountRate is out
     *                                  of range
     */
    public double applyDiscount(double price, double discountRate) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 1.");
        }
        return price - (price * discountRate);
    }
}
