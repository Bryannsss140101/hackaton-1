package org.idea.hackaton1.report.infrastructure;

import org.idea.hackaton1.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
