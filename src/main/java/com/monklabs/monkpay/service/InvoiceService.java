package com.monklabs.monkpay.service;

import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;

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
}
