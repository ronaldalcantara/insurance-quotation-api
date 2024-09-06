package com.itau.insurance_quotation_api.infrastructure.web;

import com.itau.insurance_quotation_api.application.service.QuotationService;
import com.itau.insurance_quotation_api.domain.Quotation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotacoes")
public class QuotationController {

    private final QuotationService service;

    public QuotationController(QuotationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Quotation> createQuotation(@RequestBody Quotation quotation) {
        Quotation savedQuotation = service.createQuotation(quotation);
        return ResponseEntity.ok(savedQuotation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quotation> getQuotation(@PathVariable Long id) {
        Quotation quotation = service.getQuotation(id);
        return ResponseEntity.ok(quotation);
    }
}
