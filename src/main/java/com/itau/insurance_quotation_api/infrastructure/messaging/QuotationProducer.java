package com.itau.insurance_quotation_api.infrastructure.messaging;

import com.itau.insurance_quotation_api.domain.Quotation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuotationProducer {
    private final KafkaTemplate<String, Quotation> kafkaTemplate;

    public QuotationProducer(KafkaTemplate<String, Quotation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendQuotation(Quotation quotation) {
        kafkaTemplate.send("quotation_topic", quotation);
    }
}
