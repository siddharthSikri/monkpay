package com.monklabs.monkpay.service;

import com.monklabs.monkpay.entity.Webhook;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;

import java.io.IOException;

/**
 * Invoice service interface.
 */
public interface InvoiceService {

    /**
     * Fetch all invoices.
     *
     * @return List of invoices.
     */
    Invoices fetchInvoices(PaymentStatus paymentStatus);

    /**
     * Create new invoice/s.
     *
     * @param count Number of invoices to be created.
     * @return Generic response object.
     */
    GenericResponse createInvoice(Integer count);

    /**
     * Delete invoice based on invoie identifier.
     *
     * @param invoiceId Invoice identifier.
     * @return Invoice details.
     */
    GenericResponse deleteInvoice(String invoiceId);

    /**
     * Initiate payment.
     *
     * @param invoiceId
     * @return Generic response object.
     */
    GenericResponse payment(String invoiceId);

    /**
     * Save webhook details and update invoice.
     *
     * @param webhookPaymentDetails Webhook details object.
     * @return Generic response object.
     */
    GenericResponse savePaymentWebhookDetails(Webhook webhookPaymentDetails);
}
