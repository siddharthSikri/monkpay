package com.monklabs.monkpay;

import com.monklabs.monkpay.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MonkpayApplication {

    @Autowired
    Helper helper;

    public static void main(String[] args) {
        SpringApplication.run(MonkpayApplication.class, args);
    }

    /**
     * Tasks to be completed before application start up.
     */
    @PostConstruct
    public void createMockInvoices() {
        helper.createInvoicesAndSaveToDb(3);
    }
}
