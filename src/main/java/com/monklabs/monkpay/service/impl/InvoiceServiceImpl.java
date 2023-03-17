package com.monklabs.monkpay.service.impl;

import com.monklabs.monkpay.entity.Invoice;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.helper.Helper;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;
import com.monklabs.monkpay.repository.InvoiceRepo;
import com.monklabs.monkpay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Invoice service implementation class.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    Helper helper;

    /**
     * Fetch all invoices from database.
     *
     * @param paymentStatus Current payment status of invoice/s.
     * @return List of invoices.
     */
    @Override
    public Invoices fetchInvoices(PaymentStatus paymentStatus) {
        Invoices invoices = new Invoices();
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            if (Objects.nonNull(paymentStatus)) {
                invoiceList = invoiceRepo.findAllByPaymentStatus(paymentStatus);
            } else {
                invoiceList = invoiceRepo.findAll();
            }
        } catch (Exception e) {
            // Log exception or return appropriate response.
        }
        invoices.setInvoices(invoiceList);
        invoices.setCount(invoiceList.size());
        invoices.setMessage("Fetched all invoices successfully!");
        invoices.setSuccess(true);
        return invoices;
    }

    /**
     * Create invoice/s and save to the database.
     *
     * @param count Number of invoices to be created. Default = 1.
     * @return Generic response object.
     */
    @Override
    public GenericResponse createInvoice(Integer count) {
        helper.createInvoicesAndSaveToDb(count);
        return helper.createAndPopulateGenericResponse(true, "Invoice/s created successfully!", count);
    }
}
