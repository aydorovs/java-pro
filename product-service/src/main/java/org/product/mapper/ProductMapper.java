package org.product.mapper;

import org.product.entity.ProductEntity;
import org.product.entity.UserEntity;
import org.product.model.ProductDto;
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

    public ProductEntity toProductEntity(ProductDto productDto, UserEntity userEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.id());
        productEntity.setAccountNumber(productDto.accountNumber());
        productEntity.setBalance(productDto.balance());
        productEntity.setProductType(productDto.productType());
        productEntity.setUser(userEntity);
        return productEntity;
    }
}