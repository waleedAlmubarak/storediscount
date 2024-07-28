package com.example.storediscounts.dao;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private String name;
    private boolean employee;
    private boolean affiliate;
    private double years;

}
