package com.monklabs.monkpay.controller;

import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;
import com.monklabs.monkpay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Invoice controller class consisting of all Invoice related APIs.
 */
@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    /**
     * Fetch all invoices from the database.
     *
     * @return List of invoices.
     */
    @GetMapping("/invoice")
    public Invoices fetchInvoices(
            @RequestParam(name = "payment_status", required = false) PaymentStatus paymentStatus) {
        return invoiceService.fetchInvoices(paymentStatus);
    }

    /**
     * Create a new invoice.
     *
     * @param count Number of invoices to be created.
     * @return Generic response object.
     */
    @PostMapping("/invoice")
    public GenericResponse createInvoice(
            @RequestParam(name = "count", required = false) Integer count) {
        return invoiceService.createInvoice(count);
    }
}
