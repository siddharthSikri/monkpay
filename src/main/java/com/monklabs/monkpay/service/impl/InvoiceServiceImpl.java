package com.monklabs.monkpay.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monklabs.monkpay.configs.ConfigProperties;
import com.monklabs.monkpay.entity.Invoice;
import com.monklabs.monkpay.entity.Webhook;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.helper.Constants;
import com.monklabs.monkpay.helper.Helper;
import com.monklabs.monkpay.pojo.instamojo.response.Payment;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.pojo.response.Invoices;
import com.monklabs.monkpay.repository.InvoiceRepo;
import com.monklabs.monkpay.repository.WebhookRepo;
import com.monklabs.monkpay.service.AuthorizationService;
import com.monklabs.monkpay.service.InvoiceService;
import com.monklabs.monkpay.utilities.WebUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Invoice service implementation class.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    Helper helper;

    @Autowired
    WebUtilities webUtilities;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebhookRepo webhookRepo;

    @Autowired
    AuthorizationService authorizationService;

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

    /**
     * Delete invoice based on invoice identifier or delete all invoices from the database.
     *
     * @param invoiceId Invoice identifier.
     * @return Generic response object.
     */

    @Override
    public GenericResponse deleteInvoice(String invoiceId) {
        if (StringUtils.hasLength(invoiceId)) {
            Optional<Invoice> invoiceOpt = invoiceRepo.findById(invoiceId);
            Invoice invoice = verifyAndReturnInvoiceFromOptional(invoiceOpt);
            invoiceRepo.delete(invoice);
        } else {
            invoiceRepo.deleteAll();
        }
        return helper.createAndPopulateGenericResponse(true, "Invoice/s deleted successfully!", null);
    }

    /**
     * Initiate payment implementation method.
     *
     * @param invoiceId Invoice identifier.
     * @return Generic response object.
     */
    @Override
    public GenericResponse payment(String invoiceId) {
        try {
            if (StringUtils.hasLength(invoiceId)) {
                Optional<Invoice> invoiceOpt = invoiceRepo.findById(invoiceId);
                Invoice invoice = verifyAndReturnInvoiceFromOptional(invoiceOpt);
                String reqBodyStr = createPaymentRequestString(String.valueOf(invoice.getAmount()));
                String uri = ConfigProperties.IM_BASE_URL + ConfigProperties.IM_PAYMENT_REQUEST;
                String accessToken = Constants.BEARER + authorizationService.generateAuthorizationToken();
                String response = webUtilities.post(uri, reqBodyStr, accessToken);
                Payment paymentResponse = objectMapper.readValue(response, Payment.class);
                invoice.setPaymentDetails(paymentResponse);
                invoice.setUpdated(System.currentTimeMillis());
                invoice.setPaymentStatus(PaymentStatus.INITIATED);
                invoiceRepo.save(invoice);
                return helper.createAndPopulateGenericResponse(true, "Invoice payment initiated!", null);
            } else {
                return helper.createAndPopulateGenericResponse(false, "Please provide a valid invoice ID!", null);
            }
        } catch (Exception e) {
            //Log exception and return appropriate response.
            return helper.createAndPopulateGenericResponse(false, "An exception occurred!", null);
        }
    }

    /**
     * Create payment initiation request body string.
     *
     * @param amount Item amount.
     * @return Request body string.
     */
    private String createPaymentRequestString(String amount) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("purpose=Test");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("amount=");
        strBuilder.append(amount);
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("buyer_name=Valentino");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("email=abx@xyz.com");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("phone=9999009900");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("redirect_url=http://www.google.com/");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("webhook=https://webhook.site/bb97ae83-a108-4c27-89b7-f2387ab95a6b");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("allow_repeated_payments=False");
        return strBuilder.toString();
    }

    /**
     * Fetch object from optional.
     *
     * @param invoiceOptional Invoice optional object.
     * @return Invoice details.
     */
    private Invoice verifyAndReturnInvoiceFromOptional(Optional<Invoice> invoiceOptional) {
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            return invoice;
        } else {
            return null;
        }
    }

    /**
     * Save incoming webhoook details to database and update invoice details.
     *
     * @param webhookPaymentDetails Webhook details object.
     * @return Generic response object.
     */
    @Override
    public GenericResponse savePaymentWebhookDetails(Webhook webhookPaymentDetails) {
        if (Objects.nonNull(webhookPaymentDetails)) {
            Long currentTime = System.currentTimeMillis();
            webhookPaymentDetails.setCreated(currentTime);
            webhookPaymentDetails.setUpdated(currentTime);
            webhookRepo.save(webhookPaymentDetails);
            if (StringUtils.hasLength(webhookPaymentDetails.getPaymentRequestId())) {
                String paymentRequestId = webhookPaymentDetails.getPaymentRequestId();
                Invoice invoice = invoiceRepo.findByPaymentDetails_IdIn(paymentRequestId);
                invoice.setUpdated(currentTime);
                invoice.setPaymentStatus(PaymentStatus.COMPLETED);
                invoiceRepo.save(invoice);
                return helper.createAndPopulateGenericResponse(true, "Webhook details saved and invoice updated!", null);
            }
            return helper.createAndPopulateGenericResponse(true, "Webhook details saved!", null);
        }
        return helper.createAndPopulateGenericResponse(false, "No details received in webhook request.", null);
    }
}
