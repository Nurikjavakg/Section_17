package com.eazybytes.accounts.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen",
            allocationSize = 1)

    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;

}