package org.idea.hackaton1.report.domain;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.report.infrastructure.ReportRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
}
