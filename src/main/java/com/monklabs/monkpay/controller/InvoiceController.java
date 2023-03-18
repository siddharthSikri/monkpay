package com.monklabs.monkpay.controller;

import com.monklabs.monkpay.entity.Webhook;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;
import com.monklabs.monkpay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Delete an invoice based on invoice identifier or delete all invoices.
     *
     * @param invoiceId
     * @return Generic response object.
     */
    @DeleteMapping("/invoice")
    public GenericResponse deleteInvoice(
            @RequestParam(name = "invoice_id", required = false) String invoiceId) {
        return invoiceService.deleteInvoice(invoiceId);
    }

    /**
     * Initiate payment API.
     *
     * @param invoiceId
     * @return Generic response object.
     */
    @PostMapping("/payment")
    public GenericResponse payment(
            @RequestParam(name = "invoice_id") String invoiceId) {
        return invoiceService.payment(invoiceId);
    }

    /**
     * Receive payment confirmation webhooks API.
     *
     * @param paymentDetails webhook payment details.
     * @return Generic response object.
     */
    @PostMapping(value = "/webhook/payment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public GenericResponse paymentWebhook(Webhook paymentDetails) {
        return invoiceService.savePaymentWebhookDetails(paymentDetails);
    }
}
