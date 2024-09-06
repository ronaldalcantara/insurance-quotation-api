package com.ronalcantara.insurance_quotation_api.application.service;

import com.ronalcantara.insurance_quotation_api.domain.Quotation;
import com.ronalcantara.insurance_quotation_api.infrastructure.messaging.QuotationProducer;
import com.ronalcantara.insurance_quotation_api.infrastructure.persistence.QuotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuotationServiceTest {
    @Mock
    private QuotationRepository repository;

    @Mock
    private QuotationProducer producer;

    @InjectMocks
    private QuotationService quotationService;

    private Quotation quotation;

    // Given: dada uma cotação válida - linhas 36 a 46
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quotation = Quotation.builder()
                .productId("1b2da7cc-b367-4196-8a78-9cfeec21f587")
                .offerId("adc56d77-348c-4bf0-908f-22d402ee715c")
                .totalMonthlyPremium(new BigDecimal("75.25"))
                .totalCoverageAmount(new BigDecimal("825000.00"))
                .coverages(new HashMap<>() {{
                    put("Incêndio", new BigDecimal("250000.00"));
                    put("Desastres naturais", new BigDecimal("500000.00"));
                }})
                .assistances(List.of("Encanador", "Eletricista", "Chaveiro 24h"))
                .build();
    }

    /*
        Given: uma cotação válida já está configurada no setUp
        When: chamamos o método de criação de cotação - linhas 60 e 61
        Then: a cotação deve ser salva e uma mensagem deve ser enviada ao Kafka - linhas 62 e 63
        Then: a cotação salva é a mesma que criamos  - linhas 64 e 65
    * */

    @Test
    void givenValidQuotation_whenCreateQuotation_thenSaveAndSendMessage() {

        when(repository.save(quotation)).thenReturn(quotation);
        Quotation savedQuotation = quotationService.createQuotation(quotation);
        verify(repository, times(1)).save(quotation);
        verify(producer, times(1)).sendQuotation(savedQuotation);
        assertEquals(quotation.getProductId(), savedQuotation.getProductId());
        assertEquals(quotation.getOfferId(), savedQuotation.getOfferId());
    }

    /*
    Given: cotação inválida (sem oferta válida) - linha 73
    When & Then: ao tentar criar a cotação, uma exceção deve ser lançada - linhas 74 a 76
    Verificação de chamada do repositório - linhas 78 e 79
    * */
    @Test
    void givenInvalidQuotation_whenCreateQuotation_thenThrowException() {
        quotation.setOfferId(null);
        assertThrows(Exception.class, () -> {
            quotationService.createQuotation(quotation);
        });
        verify(repository, never()).save(any(Quotation.class));
        verify(producer, never()).sendQuotation(any(Quotation.class));
    }

    /*
    Cenário de Teste adicional: para verificação de cenários positivos e negativos de retorno da cotação
    Método: getQuotation(Long id)
    * */
    @Test
    public void givenValidId_whenGetQuotation_thenReturnQuotation() {
        // Arrange
        Long validId = 1L;
        quotation.setId(validId);
        when(repository.findById(validId)).thenReturn(Optional.of(quotation));
        Quotation result = quotationService.getQuotation(validId);
        assertNotNull(result);
        assertEquals(validId, result.getId());
        verify(repository, times(1)).findById(validId);
    }

    @Test
    public void givenInvalidId_whenGetQuotation_thenThrowException() {
        Long invalidId = 2L;
        when(repository.findById(invalidId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            quotationService.getQuotation(invalidId);
        });
        assertEquals("Cotação não encontrada", exception.getMessage());
        verify(repository, times(1)).findById(invalidId);
    }
}
