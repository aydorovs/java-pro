package org.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.product.entity.ProductEntity;
import org.product.entity.UserEntity;
import org.product.mapper.ProductMapper;
import org.product.model.ProductDto;
import org.product.repository.ProductRepository;
import org.product.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getProductsByUserId(Long userId) {
        List<ProductEntity> products = productRepository.findByUserId(userId);
        if (products == null || products.isEmpty()) {
            throw new EntityNotFoundException("Products by user " + userId + "does not exist. Please create new product or user");
        }
        return products
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product does not exist. Please create new product")
        );
        return productMapper.toProductDto(productEntity);
    }

    public ProductDto createProduct(ProductDto productDto) {
        UserEntity userEntity = userRepository.findById(productDto.userId())
                .orElseThrow(
                        () -> new EntityNotFoundException("User for this product does not exist. Please create new user")
                );

        ProductEntity productEntity = productMapper.toProductEntity(productDto, userEntity);
        return productMapper.toProductDto(productRepository.save(productEntity));
    }

    public List<ProductDto> getAllUsers() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}