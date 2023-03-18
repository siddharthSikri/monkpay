package com.monklabs.monkpay.controller;

import com.monklabs.monkpay.helper.Helper;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * General controller.
 */
@RestController
public class GeneralController {

    @Autowired
    Helper helper;

    /**
     * Health check endpoint.
     *
     * @return Generic response object.
     */
    @GetMapping("/health")
    public GenericResponse healthCheck() {
        return helper.createAndPopulateGenericResponse(true, "Greetings from MonkPay!", null);
    }
}
