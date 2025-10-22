package org.idea.hackaton1.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private String branch; // Opcional para usuarios CENTRAL, inferido para BRANCH

    @NotBlank(message = "emailTo es obligatorio")
    @Email(message = "emailTo debe ser un email válido")
    private String emailTo;

    // Método helper para obtener fecha from o valor por defecto
    public LocalDate getFromDateOrDefault() {
        return fromDate != null ? fromDate : LocalDate.now().minusDays(7);
    }

    // Método helper para obtener fecha to o valor por defecto
    public LocalDate getToDateOrDefault() {
        return toDate != null ? toDate : LocalDate.now();
    }
}
