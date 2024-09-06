package com.itau.insurance_quotation_api.infrastructure.persistence;

import com.itau.insurance_quotation_api.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
}
