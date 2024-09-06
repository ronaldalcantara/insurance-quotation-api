package com.ronalcantara.insurance_quotation_api.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Entity
public class Quotation {
    @Id
    private Long id;
    private String productId;
    private String offerId;
    private BigDecimal totalMonthlyPremium;
    private BigDecimal totalCoverageAmount;

    @ElementCollection
    @CollectionTable(name = "quotation_details", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value")
    private Map<String, BigDecimal> coverages;

    private List<String> assistances;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
