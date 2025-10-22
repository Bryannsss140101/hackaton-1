package org.idea.hackaton1.sale.infrastructure;

import org.idea.hackaton1.sale.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("""
                SELECT s FROM Sale s
                WHERE (:branch IS NULL OR s.branch = :branch)
                AND (:from IS NULL OR s.soldAt >= :from)
                AND (:to IS NULL OR s.soldAt <= :to)
            """)
    Page<Object> findByFilter(String branch, LocalDateTime from, LocalDateTime to, PageRequest pageRequest);
}
