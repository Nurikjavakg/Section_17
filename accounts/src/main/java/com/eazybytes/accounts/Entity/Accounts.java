package com.eazybytes.accounts.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {
    private Long customerId;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_gen")
    @SequenceGenerator(name = "account_gen",
            allocationSize = 1)

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Boolean communicationSw;
}