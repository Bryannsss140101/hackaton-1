package org.idea.hackaton1.sale.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false, length = 50)
    private String sku;

    @Column(nullable = false)
    private Integer units;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private LocalDate soldAt;

    @Column(nullable = false)
    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}