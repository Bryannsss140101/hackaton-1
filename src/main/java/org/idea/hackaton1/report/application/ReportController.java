package org.idea.hackaton1.report.application;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.report.domain.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
}
