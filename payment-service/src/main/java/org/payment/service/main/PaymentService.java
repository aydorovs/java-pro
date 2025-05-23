package org.payment.service.main;

import lombok.extern.slf4j.Slf4j;
import org.payment.exception.PaymentServiceInsufficientFundsException;
import org.payment.exception.PaymentServiceProductNotFoundException;
import org.payment.exception.ProductServiceInternalErrorException;
import org.payment.model.PaymentRequest;
import org.payment.model.PaymentResponse;
import org.payment.model.ProductResponse;
import org.payment.service.external.ProductServiceClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    private final ProductServiceClient productServiceClient;

    public PaymentService(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        ProductResponse product = getProduct(paymentRequest);
        checkBalance(paymentRequest, product);
        log.info("Payment processed: productId={}}, amount={}}", paymentRequest.productId(), paymentRequest.amount());
        return new PaymentResponse("Payment processed successfully");
    }

    private void checkBalance(PaymentRequest paymentRequest, ProductResponse product) {
        if (product.balance().compareTo(paymentRequest.amount()) < 0) {
            throw new PaymentServiceInsufficientFundsException(
                    String.format("Current balance: %s, required: %s",
                            product.balance(), paymentRequest.amount())
            );
        }
    }

    private ProductResponse getProduct(PaymentRequest paymentRequest) {
        ProductResponse product;
        try {
            product = productServiceClient.getProductById(paymentRequest.productId());
        } catch (ProductServiceInternalErrorException e) {
            throw new PaymentServiceProductNotFoundException("Product not found or service unavailable");
        }
        return product;
    }
}