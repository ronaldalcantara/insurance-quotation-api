package com.itau.insurance_quotation_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class Customer {
    @Id
    private Long customerId;
    private String documentNumber;
    private String name;
    private String type;
    private String gender;
    private LocalDateTime dateOfBirth;
    private String email;
    private String phoneNumber;
}
