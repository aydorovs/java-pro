package org.lessons.lesson4.mapper;

import org.lessons.lesson4.entity.ProductEntity;
import org.lessons.lesson4.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toProductDto(ProductEntity productEntity) {
        return ProductDto
                .builder()
                .id(productEntity.getId())
                .accountNumber(productEntity.getAccountNumber())
                .balance(productEntity.getBalance())
                .productType(productEntity.getProductType())
                .userId(productEntity.getUser().getId())
                .build();
    }
}