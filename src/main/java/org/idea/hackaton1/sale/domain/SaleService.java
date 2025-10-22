package org.idea.hackaton1.sale.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.sale.dto.SaleRequestDTO;
import org.idea.hackaton1.sale.dto.SaleResponseDTO;
import org.idea.hackaton1.sale.infrastructure.SaleRepository;
import org.idea.hackaton1.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO) {
        var sale = modelMapper.map(saleRequestDTO, Sale.class);

        return modelMapper.map(sale, SaleResponseDTO.class);
    }

    public SaleResponseDTO getSale(Long id) {
        var sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        return modelMapper.map(sale, SaleResponseDTO.class);
    }

    public Page<SaleResponseDTO> getAllSales(String branch, LocalDateTime from, LocalDateTime to, PageRequest pageRequest) {
        var sales = saleRepository.findByFilter(branch, from, to, pageRequest);

        return sales.map(sale -> modelMapper.map(sale, SaleResponseDTO.class));
    }

    public SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO) {
        var sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        modelMapper.map(saleRequestDTO, sale);

        return modelMapper.map(sale, SaleResponseDTO.class);
    }

    public void deleteSale(Long id) {
        var sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        saleRepository.delete(sale);
    }

}
