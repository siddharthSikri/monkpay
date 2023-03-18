package com.monklabs.monkpay;

import com.monklabs.monkpay.helper.Helper;
import com.monklabs.monkpay.repository.WebhookRepo;
import com.monklabs.monkpay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

/**
 * MonkPay main class.
 */
@SpringBootApplication
public class MonkpayApplication {

    @Autowired
    Helper helper;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    WebhookRepo webhookRepo;

    public static void main(String[] args) {
        SpringApplication.run(MonkpayApplication.class, args);
    }

    /**
     * Tasks to be completed before application start up.
     */
    @PostConstruct
    public void createMockInvoices() {
        webhookRepo.deleteAll();
        invoiceService.deleteInvoice(null);
        helper.createInvoicesAndSaveToDb(3);
    }
}
