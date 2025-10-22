package org.idea.hackaton1.sale.infrastructure;

import org.idea.hackaton1.sale.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Page<Object> findByFilter(String branch, LocalDateTime from, LocalDateTime to, PageRequest pageRequest);
}
