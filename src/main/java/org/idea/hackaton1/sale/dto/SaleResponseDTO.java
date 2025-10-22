package org.idea.hackaton1.sale.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleResponseDTO {

    private Long id;

    private String sku;

    private Integer units;
    private Double price;
    private String branch;

    private LocalDate soldDate;

    private String createdBy;
}
