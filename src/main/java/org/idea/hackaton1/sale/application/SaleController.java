package org.idea.hackaton1.sale.application;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.sale.domain.SaleService;
import org.idea.hackaton1.sale.dto.SaleRequestDTO;
import org.idea.hackaton1.sale.dto.SaleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        var sale = saleService.createSale(saleRequestDTO);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSale(@PathVariable Long id) {
        var sale = saleService.getSale(id);
        return ResponseEntity.ok(sale);
    }


    @GetMapping
    public ResponseEntity<Page<SaleResponseDTO>> getAllSales(
            @RequestParam String branch,
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to,
            @RequestParam(value = "0") Integer page,
            @RequestParam(value = "10") Integer size) {
        var sales = saleService.getAllSales(branch, from, to, PageRequest.of(page, size));
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(
            @PathVariable Long id,
            @RequestBody SaleRequestDTO saleRequestDTO
    ) {
        var sale = saleService.updateSale(id, saleRequestDTO);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
