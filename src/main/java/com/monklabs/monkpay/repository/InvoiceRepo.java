package com.monklabs.monkpay.repository;

import com.monklabs.monkpay.entity.Invoice;
import com.monklabs.monkpay.enums.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Invoice collection repository class.
 */
@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, String> {

    /**
     * Fetch all invoices.
     *
     * @return
     */
    List<Invoice> findAll();

    /**
     * Fetch all invoices based on payment status.
     *
     * @param paymentStatus PENDING, INITIATED, COMPLETED.
     * @return List of invoices.
     */
    List<Invoice> findAllByPaymentStatus(PaymentStatus paymentStatus);
}
