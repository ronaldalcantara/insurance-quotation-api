package com.ronalcantara.insurance_quotation_api.application.service;

import com.ronalcantara.insurance_quotation_api.domain.Quotation;
import com.ronalcantara.insurance_quotation_api.infrastructure.messaging.QuotationProducer;
import com.ronalcantara.insurance_quotation_api.infrastructure.persistence.QuotationRepository;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final QuotationRepository repository;
    private final QuotationProducer producer;

    public QuotationService(QuotationRepository repository, QuotationProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public Quotation createQuotation(Quotation quotation) {
        if (quotation.getOfferId() == null) {
            throw new IllegalArgumentException("Offer Id não pode ser nulo.");
        }else{
            Quotation savedQuotation = repository.save(quotation);
            producer.sendQuotation(savedQuotation);
            return savedQuotation;
        }
    }
    public Quotation getQuotation(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cotação não encontrada"));
    }
}
