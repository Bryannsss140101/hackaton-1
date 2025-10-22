package org.idea.hackaton1.sale.infrastructure;

import org.idea.hackaton1.sale.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
