package org.payment.service.external;

import org.payment.exception.PaymentServiceProductNotFoundException;
import org.payment.exception.PaymentServiceUserProductsNotFoundException;
import org.payment.exception.ProductServiceInternalErrorException;
import org.payment.model.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductServiceClient {

    private final RestClient restClient;

    public ProductServiceClient(RestClient productServiceRestClient) {
        this.restClient = productServiceRestClient;
    }

    public ProductResponse getProductById(Long productId) {
        return restClient.get()
                .uri("/api/v1/products/{productId}", productId)
                .exchange((request, response) -> {
                            if (response.getStatusCode().value() == 404) {
                                throw new PaymentServiceProductNotFoundException("Product with id " + productId + " not found");
                            } else if (!response.getStatusCode().is2xxSuccessful()) {
                                throw new ProductServiceInternalErrorException("Failed to get product with id: " + productId);
                            } else {
                                ProductResponse productResponse;
                                try {
                                    productResponse = response.bodyTo(ProductResponse.class);
                                } catch (Exception e) {
                                    throw new ProductServiceInternalErrorException("Invalid structure productResponse by id: " + productId);
                                }
                                return productResponse;
                            }
                        }
                );
    }

    public ProductResponse[] getProductsByUserId(Long userId) {
        return restClient.get()
                .uri("/api/v1/products/user/{userId}", userId)
                .exchange((request, response) -> {
                            if (response.getStatusCode().value() == 404) {
                                throw new PaymentServiceUserProductsNotFoundException("Products for user with id " + userId + " not found");
                            } else if (!response.getStatusCode().is2xxSuccessful()) {
                                throw new ProductServiceInternalErrorException("Failed to get products for user with id: " + userId);
                            } else {
                                ProductResponse[] productResponse;
                                try {
                                    productResponse = response.bodyTo(ProductResponse[].class);
                                } catch (Exception e) {
                                    throw new ProductServiceInternalErrorException("Invalid structure productResponse for user with id: " + userId);
                                }
                                return productResponse;
                            }
                        }
                );
    }
}