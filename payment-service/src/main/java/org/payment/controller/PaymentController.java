package org.payment.controller;

import org.payment.model.PaymentRequest;
import org.payment.model.PaymentResponse;
import org.payment.model.ProductResponse;
import org.payment.service.external.ProductServiceClient;
import org.payment.service.main.PaymentService;
import org.payment.validator.PaymentRequestValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ProductServiceClient productServiceClient;
    private final PaymentRequestValidator paymentRequestValidator;

    public PaymentController(PaymentService paymentService,
                             ProductServiceClient productServiceClient, PaymentRequestValidator paymentRequestValidator) {
        this.paymentService = paymentService;
        this.productServiceClient = productServiceClient;
        this.paymentRequestValidator = paymentRequestValidator;
    }

    @PostMapping
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequestValidator.validate(paymentRequest);
        return paymentService.processPayment(paymentRequest);
    }

    @GetMapping("/products/users/{userId}")
    public ProductResponse[] getProductsByUserId(@PathVariable("userId") Long userId) {
        return productServiceClient.getProductsByUserId(userId);
    }

    @GetMapping("/products/{productId}")
    public ProductResponse getProductById(@PathVariable("productId") Long productId) {
        return productServiceClient.getProductById(productId);
    }
}