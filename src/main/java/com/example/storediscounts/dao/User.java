package com.example.storediscounts.dao;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Represents a user in the store discounts application.
 *
 * <p>
 * The {@code User} class includes the user's name, whether they are an employee
 * or affiliate, and the number of years they have been a customer.
 * </p>
 *
 * <p>
 * This class uses Lombok annotations for boilerplate code generation:
 * {@link Data}, {@link AllArgsConstructor}, and {@link Builder}.
 * </p>
 */

@Data
@AllArgsConstructor
@Builder
public class User {

    /**
     * The name of the user.
     */
    private String name;

    /**
     * Indicates whether the user is an employee.
     */
    private boolean employee;

    /**
     * Indicates whether the user is an affiliate.
     */
    private boolean affiliate;

    /**
     * The number of years the user has been a customer.
     */
    private double years;

}
