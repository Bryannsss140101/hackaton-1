package org.idea.hackaton1.sale.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.sale.domain.SaleService;
import org.idea.hackaton1.sale.dto.SaleRequestDTO;
import org.idea.hackaton1.sale.dto.SaleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody @Valid SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO saleResponseDTO = saleService.createSale(saleRequestDTO);
    }
}
