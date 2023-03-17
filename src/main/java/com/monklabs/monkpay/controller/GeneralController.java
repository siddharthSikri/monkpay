package com.monklabs.monkpay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * General controller.
 */
@RestController
public class GeneralController {

    /**
     * Health check endpoint.
     *
     * @return
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Greetings from MonkPay!";
    }
}
