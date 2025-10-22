package org.idea.hackaton1.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.idea.hackaton1.report.domain.Report;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {

    private String requestId;

    private String status;

    private String message;

    private String estimatedTime;

    private LocalDateTime requestedAt;
}
