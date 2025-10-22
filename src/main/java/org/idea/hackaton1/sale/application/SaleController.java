package org.idea.hackaton1.sale.application;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.sale.domain.SaleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class SaleController {
    private final SaleService saleService;

    


}
