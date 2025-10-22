package org.idea.hackaton1.sale.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleRequestDTO {

    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 50, message = "El SKU no puede tener más de 50 caracteres")
    private String sku;

    @PositiveOrZero
    private Integer units;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double price;

    @NotBlank(message = "La sucursal es obligatoria")
    private String branch;

    private LocalDate soldAlt;
}
